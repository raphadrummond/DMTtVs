package databit.com.br.datamobile.ftp;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import databit.com.br.datamobile.dao.ArquivoDAO;
import databit.com.br.datamobile.dao.ConfigFTPDAO;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.domain.ConfigFTP;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsclient.ArquivoWSClient;


public class MyFTP   {

    private FTPClient ftp = null;
    private String retorno = null;
    public Os os;


    public MyFTP() {
        this.ftp = new FTPClient();
        this.retorno = "";
        try {
            ConfigFTPDAO configFTPDAO = new ConfigFTPDAO();
            ConfigFTP configFTP = configFTPDAO.procuraFtp("id = 1");
            DateFormat formato = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            this.retorno = formato.format(date) + " - Iniciando Conexão" + "\n";
            this.ftp.connect(configFTP.getEndereco().toString());
            if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                this.ftp.login(configFTP.getUsuario().toString(), configFTP.getSenha().toString());
            } else {
                this.ftp.disconnect();
            }
        } catch (Exception e) {
            this.retorno = " Ocorreu um erro: " + e.getMessage() + "\n";
        }
    }

    public String incluirFTP(String sArquivo, Uri uri, Context context) throws IOException {
        try {
            ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
            Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = "+os.getOperacaomobile().toString());
            try {
                this.ftp.makeDirectory(configmobile.getPastaftp().toString()+"/");
            } catch (Exception e) {
                this.retorno = " Ocorreu um erro: " + e.getMessage() + "\n";
            }
            try {
                this.ftp.makeDirectory(configmobile.getPastaftp().toString()+ "/" + os.getCodigo().toString() + "/");
            } catch (Exception e) {
                this.retorno = " Ocorreu um erro: " + e.getMessage() + "\n";
            }
            this.ftp.changeWorkingDirectory(configmobile.getPastaftp().toString() + "/"  + os.getCodigo().toString() + "/");
            ContentResolver cr =  context.getContentResolver();
            InputStream arqEnviar = cr.openInputStream(uri);
            this.ftp.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
            this.ftp.setFileType(FTPClient.STREAM_TRANSFER_MODE);
            if (sArquivo.endsWith(".txt") || sArquivo.endsWith(".TXT")) {
                this.ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
            } else {
                this.ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            }
            this.ftp.storeFile(sArquivo, arqEnviar);
            arqEnviar.close();
            this.ftp.logout();
            this.ftp.disconnect();

            ArquivoDAO arquivoDAO = new ArquivoDAO();
            Arquivo arquivo = new Arquivo();
            OsDAO osDAO = new OsDAO();
            if (os.getIdarquivo() != null) {
                os.setIdarquivo(os.getIdarquivo() + 1);
            } else {
                os.setIdarquivo(1);
            }
            osDAO.createOrUpdate(os);
            arquivo.setId(os.getBanco().toString() +
                          os.getCodigo().toString() +
                          os.getOperacaomobile().toString() +
                          os.getIdarquivo().toString());
            arquivo.setBanco(os.getBanco());
            arquivo.setItem(os.getIdarquivo());
            arquivo.setArquivo(sArquivo);
            arquivo.setCodigo(os.getCodigo().toString());
            arquivo.setDiretorio(uri.toString());
            arquivo.setOperacaomobile(os.getOperacaomobile());

            ArquivoWSClient arquivoWSClient = new ArquivoWSClient();
            String sResultado = arquivoWSClient.incluirArquivo(arquivo);
            if (sResultado.equals("OK")) {
                arquivoDAO.createOrUpdate(arquivo);
                this.retorno = "OK";
            }
            else {
                this.retorno = sResultado;
                arquivoDAO.delete(arquivo);
            }

        } catch (Exception e) {
            this.retorno = " Ocorreu um erro: " + e.getMessage() + "\n";
        }
        return retorno;
    }

    public String excluirFTP(String sArquivo) throws IOException {
        try {
            ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
            Configmobile configmobile = configmobileDAO.procuraConfigmobile("id = "+os.getOperacaomobile().toString());
            Date date = new Date();
            try {
                this.ftp.makeDirectory(configmobile.getPastaftp().toString()+"/");
            } catch (Exception e) {
            }
            try {
                this.ftp.makeDirectory(configmobile.getPastaftp().toString()+ "/" + os.getCodigo().toString() + "/");
            } catch (Exception e) {
            }

            this.ftp.changeWorkingDirectory(configmobile.getPastaftp().toString()+ "/" + os.getCodigo().toString() + "/");

            this.ftp.deleteFile(ftp.printWorkingDirectory()+"/"+sArquivo);

            this.ftp.logout();
            this.ftp.disconnect();

            ArquivoDAO arquivoDAO = new ArquivoDAO();
            Arquivo arquivo = arquivoDAO.procuraArquivo("arquivo = '"+sArquivo+"' " +
                                                        " and codigo = '"+os.getCodigo().toString()+"' "+
                                                        " and banco = '"+os.getBanco().toString()+"' "+
                                                        " and operacaomobile = "+os.getOperacaomobile().toString());


            ArquivoWSClient arquivoWSClient = new ArquivoWSClient();
            String sResultado = arquivoWSClient.excluirArquivo(arquivo);
            if (sResultado.equals("OK")) {
                arquivoDAO.delete(arquivo);
                this.retorno = "OK";
            }
            else {
                this.retorno = sResultado;
            }
        } catch (Exception e) {
            this.retorno = " Ocorreu um erro: " + e.getMessage() + "\n";
        }
        return retorno;
    }


}

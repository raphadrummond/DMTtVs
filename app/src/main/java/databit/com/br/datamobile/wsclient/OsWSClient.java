package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.LogsincDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Logsinc;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 27/04/2016.
 */
public class OsWSClient extends AndroidWsClient {

    public Usuario usuario;

    public List<Os> buscaOS() {
        String json = wsGet("recebeOS/" + usuario.getNome().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Os>>() {}.getType();
        List<Os> retorno = gson.fromJson(json, collectionType);

        return retorno;
    }

    public String enviaCancelamento(Os os) {
        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("enviaCancelamento", "{\"result\":[["+jsonOs+"]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        switch (os.getOperacaomobile()) {
            case 1: {
               logsinc.setEvento("Cancelamento de OS");       
               break;
            }
            case 2: {
                logsinc.setEvento("Cancelamento de Requisição");
                break;
            }
            case 3: {
                logsinc.setEvento("Cancelamento de Pedido");
                break;
            }
            case 4: {
                logsinc.setEvento("Cancelamento de TI");
                break;
            }
            case 5: {
                logsinc.setEvento("Cancelamento de Implantação");
                break;
            }
            case 6: {
                logsinc.setEvento("Cancelamento de Projeto");
                break;
            }
            case 7: {
                logsinc.setEvento("Cancelamento de Atendimento");
                break;
            }
        }  
        logsinc.setRetorno(sRetorno);
        logsinc.setOperacaomobile(os.getOperacaomobile());
        if (sRetorno.equals("OK")) {
           logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsincDAO.gravaLog(logsinc);
        return sRetorno;
    }

    public String enviaDesfazer(Os os) {
        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("enviaDesfazer", "{\"result\":[[" + jsonOs + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        LogsincDAO logsincDAO = new LogsincDAO();

        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        switch (os.getOperacaomobile()) {
            case 1: {
                logsinc.setEvento("Desfazer OS");
                break;
            }
            case 2: {
                logsinc.setEvento("Desfazer Requisição");
                break;
            }
            case 3: {
                logsinc.setEvento("Desfazer Pedido");
                break;
            }
            case 4: {
                logsinc.setEvento("Desfazer TI");
                break;
            }
            case 5: {
                logsinc.setEvento("Desfazer Implantação");
                break;
            }
            case 6: {
                logsinc.setEvento("Desfazer Projeto");
                break;
            }
            case 7: {
                logsinc.setEvento("Desfazer Atendimento");
                break;
            }
        }
        logsinc.setRetorno(sRetorno);
        logsinc.setOperacaomobile(os.getOperacaomobile());
        if (sRetorno.equals("OK")) {
            logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsincDAO.gravaLog(logsinc);

        return sRetorno;
    }

    public String enviaStatusos(String sOs, String sBanco, String sCodtec) {


        OsDAO osDAO = new OsDAO();
        Os os = osDAO.procuraOs("codigo = '"+sOs+"' and banco = '"+sBanco+"' ");
        String sRetorno = "";
        if (os != null) {
            LogsincDAO logsincDAO = new LogsincDAO();
            Logsinc logsinc = new Logsinc();
            Date date = new Date();
            logsinc.setBanco(os.getBanco());
            logsinc.setOs(os.getCodigo());
            logsinc.setData(date);

            switch (os.getOperacaomobile()) {
                case 1: {
                    logsinc.setEvento("Consultar OS");
                    sRetorno = wsGet("statusOS/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 2: {
                    logsinc.setEvento("Consultar Requisição");
                    sRetorno = wsGet("statusVEN/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 3: {
                    logsinc.setEvento("Consultar Pedido");
                    sRetorno = wsGet("statusCOM/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 4: {
                    logsinc.setEvento("Consultar TI");
                    sRetorno = wsGet("statusTI/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 5: {
                    logsinc.setEvento("Consultar Implantação");
                    sRetorno = wsGet("statusTI/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 6: {
                    logsinc.setEvento("Consultar Projeto");
                    sRetorno = wsGet("statusTI/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
                case 7: {
                    logsinc.setEvento("Consultar Atendimento");
                    sRetorno = wsGet("statusTEL/" + sOs + "/" + sBanco + "/" + sCodtec);
                    break;
                }
            }
            sRetorno = sRetorno.replace("{\"result\":[\"","");
            sRetorno = sRetorno.replace("\"]}","");
            logsinc.setRetorno(sRetorno);
            logsinc.setOperacaomobile(os.getOperacaomobile());
            if (sRetorno.equals("OK")) {
                logsinc.setErro(0);
            }
            else {
                logsinc.setErro(1);
            }
            logsincDAO.gravaLog(logsinc);
        }

        return sRetorno;
    }

    public String enviaOS(Os os) {
        OsDAO osDAO = new OsDAO();
        AparelhoDAO aparelhoDAO = new AparelhoDAO();
        Aparelho aparelho = aparelhoDAO.procuraAparelho("id = 1");

        if (os.getObs() != null) {
            os.setObs(os.getObs().replace("\n", " "));
            os.setObs(os.getObs().replace("[\\]", "/"));
        }
        if (os.getObstrocada() != null) {
            os.setObstrocada(os.getObstrocada().replace("\n", " "));
            os.setObstrocada(os.getObstrocada().replace("[\\]", "/"));
        }
        if (os.getObsequip() != null) {
            os.setObsequip(os.getObsequip().replace("\n", " "));
            os.setObsequip(os.getObsequip().replace("[\\]", "/"));
        }
        if (os.getObsfecha() != null) {
            os.setObsfecha(os.getObsfecha().replace("\n", " "));
            os.setObsfecha(os.getObsfecha().replace("[\\]", "/"));
        }
        if (os.getObscanc() != null) {
            os.setObscanc(os.getObscanc().replace("\n", " "));
            os.setObscanc(os.getObscanc().replace("[\\]", "/"));
        }
        if (os.getObspendente() != null) {
            os.setObspendente(os.getObspendente().replace("\n", " "));
            os.setObspendente(os.getObspendente().replace("[\\]", "/"));
        }
        if (os.getContato() != null) {
            os.setContato(os.getContato().replace("\n", " "));
            os.setContato(os.getContato().replace("[\\]", "/"));
        }
        osDAO.gravaOs(os);

        String jsonOs = gson.toJson(os);
        String sRetorno = "";
        switch (os.getOperacaomobile()) {
            case 1: {
                sRetorno = wsPost("enviaOS", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 2: {
                sRetorno = wsPost("enviaREQ", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 3: {
                sRetorno = wsPost("enviaCOM", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 4: {
                sRetorno = wsPost("enviaTI", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 5: {
                sRetorno = wsPost("enviaTI", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 6: {
                sRetorno = wsPost("enviaTI", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
            case 7: {
                sRetorno = wsPost("enviaTEL", "{\"result\":[[" + jsonOs + "]]}");
                break;
            }
        }
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        switch (os.getOperacaomobile()) {
            case 1: {
                logsinc.setEvento("Enviar OS");
                break;
            }
            case 2: {
                logsinc.setEvento("Enviar Requisição");
                break;
            }
            case 3: {
                logsinc.setEvento("Enviar Pedido");
                break;
            }
            case 4: {
                logsinc.setEvento("Enviar TI");
                break;
            }
            case 5: {
                logsinc.setEvento("Enviar Implantação");
                break;
            }
            case 6: {
                logsinc.setEvento("Enviar Projeto");
                break;
            }
            case 7: {
                logsinc.setEvento("Enviar Atendimento");
                break;
            }
        }
        logsinc.setRetorno(sRetorno);
        logsinc.setOperacaomobile(os.getOperacaomobile());
        if (sRetorno.equals("OK")) {
            logsinc.setErro(0);

            if (os.getCheckinok() == 1) {
                if (os.getStatus_check().equals(4)) {
                    if (aparelho.getData().before(os.getDataout())) {
                        aparelho.setLatitude(os.getLatitudeout());
                        aparelho.setLongitude(os.getLongitudeout());
                        aparelho.setData(os.getDataout());
                        aparelhoDAO.gravaAparelho(aparelho);
                        AparelhoWSClient aparelhoWSClient = new AparelhoWSClient();
                        aparelhoWSClient.enviaLocalidade(aparelho);
                    }
                }
                else {
                    if (aparelho.getData().before(os.getDatain())) {
                        aparelho.setLatitude(os.getLatitudein());
                        aparelho.setLongitude(os.getLongitudein());
                        aparelho.setData(os.getDatain());
                        aparelhoDAO.gravaAparelho(aparelho);
                        AparelhoWSClient aparelhoWSClient = new AparelhoWSClient();
                        aparelhoWSClient.enviaLocalidade(aparelho);
                    }
                }
            }
        }
        else {
            logsinc.setErro(1);
        }
        logsincDAO.gravaLog(logsinc);


        return sRetorno;
    }

    public String gerarRequisicao(Os os) {
        OsDAO osDAO = new OsDAO();
        if (os.getObs() != null) {
            os.setObs(os.getObs().replace("\n", " "));
            os.setObs(os.getObs().replace("[\\]", "/"));
        }
        if (os.getObstrocada() != null) {
            os.setObstrocada(os.getObstrocada().replace("\n", " "));
            os.setObstrocada(os.getObstrocada().replace("[\\]", "/"));
        }
        if (os.getObsequip() != null) {
            os.setObsequip(os.getObsequip().replace("\n", " "));
            os.setObsequip(os.getObsequip().replace("[\\]", "/"));
        }
        if (os.getObsfecha() != null) {
            os.setObsfecha(os.getObsfecha().replace("\n", " "));
            os.setObsfecha(os.getObsfecha().replace("[\\]", "/"));
        }
        if (os.getObscanc() != null) {
            os.setObscanc(os.getObscanc().replace("\n", " "));
            os.setObscanc(os.getObscanc().replace("[\\]", "/"));
        }
        if (os.getObspendente() != null) {
            os.setObspendente(os.getObspendente().replace("\n", " "));
            os.setObspendente(os.getObspendente().replace("[\\]", "/"));
        }
        if (os.getContato() != null) {
            os.setContato(os.getContato().replace("\n", " "));
            os.setContato(os.getContato().replace("[\\]", "/"));
        }
        osDAO.gravaOs(os);

        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("Requisicao", "{\"result\":[[" + jsonOs + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        logsinc.setEvento("Gerar Requisição");
        logsinc.setRetorno(sRetorno);
        if (sRetorno.substring(0,2).equals("OK")) {
            logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsinc.setOperacaomobile(os.getOperacaomobile());
        logsincDAO.gravaLog(logsinc);
        return sRetorno;
    }


    public String gerarRecolha(Os os) {
        OsDAO osDAO = new OsDAO();
        if (os.getObs() != null) {
            os.setObs(os.getObs().replace("\n", " "));
            os.setObs(os.getObs().replace("[\\]", "/"));
        }
        if (os.getObstrocada() != null) {
            os.setObstrocada(os.getObstrocada().replace("\n", " "));
            os.setObstrocada(os.getObstrocada().replace("[\\]", "/"));
        }
        if (os.getObsequip() != null) {
            os.setObsequip(os.getObsequip().replace("\n", " "));
            os.setObsequip(os.getObsequip().replace("[\\]", "/"));
        }
        if (os.getObsfecha() != null) {
            os.setObsfecha(os.getObsfecha().replace("\n", " "));
            os.setObsfecha(os.getObsfecha().replace("[\\]", "/"));
        }
        if (os.getObscanc() != null) {
            os.setObscanc(os.getObscanc().replace("\n", " "));
            os.setObscanc(os.getObscanc().replace("[\\]", "/"));
        }
        if (os.getObspendente() != null) {
            os.setObspendente(os.getObspendente().replace("\n", " "));
            os.setObspendente(os.getObspendente().replace("[\\]", "/"));
        }
        if (os.getContato() != null) {
            os.setContato(os.getContato().replace("\n", " "));
            os.setContato(os.getContato().replace("[\\]", "/"));
        }
        osDAO.gravaOs(os);

        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("Recolha", "{\"result\":[[" + jsonOs + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        logsinc.setEvento("Gerar Recolha");
        logsinc.setRetorno(sRetorno);
        if (sRetorno.substring(0,2).equals("OK")) {
            logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsinc.setOperacaomobile(os.getOperacaomobile());
        logsincDAO.gravaLog(logsinc);
        return sRetorno;
    }



    public String alterarOrdem(Os os) {
        OsDAO osDAO = new OsDAO();
        if (os.getObs() != null) {
            os.setObs(os.getObs().replace("\n", " "));
            os.setObs(os.getObs().replace("[\\]", "/"));
        }
        if (os.getObstrocada() != null) {
            os.setObstrocada(os.getObstrocada().replace("\n", " "));
            os.setObstrocada(os.getObstrocada().replace("[\\]", "/"));
        }
        if (os.getObsequip() != null) {
            os.setObsequip(os.getObsequip().replace("\n", " "));
            os.setObsequip(os.getObsequip().replace("[\\]", "/"));
        }
        if (os.getObsfecha() != null) {
            os.setObsfecha(os.getObsfecha().replace("\n", " "));
            os.setObsfecha(os.getObsfecha().replace("[\\]", "/"));
        }
        if (os.getObscanc() != null) {
            os.setObscanc(os.getObscanc().replace("\n", " "));
            os.setObscanc(os.getObscanc().replace("[\\]", "/"));
        }
        if (os.getObspendente() != null) {
            os.setObspendente(os.getObspendente().replace("\n", " "));
            os.setObspendente(os.getObspendente().replace("[\\]", "/"));
        }
        if (os.getContato() != null) {
            os.setContato(os.getContato().replace("\n", " "));
            os.setContato(os.getContato().replace("[\\]", "/"));
        }
        osDAO.gravaOs(os);

        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("Alterarordem", "{\"result\":[[" + jsonOs + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        logsinc.setEvento("Alterar Ordem");
        logsinc.setOperacaomobile(os.getOperacaomobile());
        logsinc.setRetorno(sRetorno);
        if (sRetorno.equals("OK")) {
            logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsincDAO.gravaLog(logsinc);

        return sRetorno;
    }

    public String enviarEmail(Os os) {
        OsDAO osDAO = new OsDAO();
        if (os.getObs() != null) {
            os.setObs(os.getObs().replace("\n", " "));
            os.setObs(os.getObs().replace("[\\]", "/"));
        }
        if (os.getObstrocada() != null) {
            os.setObstrocada(os.getObstrocada().replace("\n", " "));
            os.setObstrocada(os.getObstrocada().replace("[\\]", "/"));
        }
        if (os.getObsequip() != null) {
            os.setObsequip(os.getObsequip().replace("\n", " "));
            os.setObsequip(os.getObsequip().replace("[\\]", "/"));
        }
        if (os.getObsfecha() != null) {
            os.setObsfecha(os.getObsfecha().replace("\n", " "));
            os.setObsfecha(os.getObsfecha().replace("[\\]", "/"));
        }
        if (os.getObscanc() != null) {
            os.setObscanc(os.getObscanc().replace("\n", " "));
            os.setObscanc(os.getObscanc().replace("[\\]", "/"));
        }
        if (os.getObspendente() != null) {
            os.setObspendente(os.getObspendente().replace("\n", " "));
            os.setObspendente(os.getObspendente().replace("[\\]", "/"));
        }
        if (os.getContato() != null) {
            os.setContato(os.getContato().replace("\n", " "));
            os.setContato(os.getContato().replace("[\\]", "/"));
        }
        osDAO.gravaOs(os);

        String jsonOs = gson.toJson(os);
        String sRetorno = wsPost("Enviaremail", "{\"result\":[[" + jsonOs + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");

        LogsincDAO logsincDAO = new LogsincDAO();
        Logsinc logsinc = new Logsinc();
        Date date = new Date();
        logsinc.setBanco(os.getBanco());
        logsinc.setOs(os.getCodigo());
        logsinc.setData(date);
        logsinc.setEvento("Enviar Email");
        logsinc.setOperacaomobile(os.getOperacaomobile());
        logsinc.setRetorno(sRetorno);
        if (sRetorno.equals("OK")) {
            logsinc.setErro(0);
        }
        else {
            logsinc.setErro(1);
        }
        logsincDAO.gravaLog(logsinc);

        return sRetorno;
    }

}

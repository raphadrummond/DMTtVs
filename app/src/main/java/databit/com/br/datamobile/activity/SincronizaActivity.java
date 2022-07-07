package databit.com.br.datamobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.comparator.OsComparator;
import databit.com.br.datamobile.dao.ComposicaoDAO;
import databit.com.br.datamobile.dao.CondicaoDAO;
import databit.com.br.datamobile.dao.ConfigFTPDAO;
import databit.com.br.datamobile.dao.ConfigmobileDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.OsDAO;
import databit.com.br.datamobile.dao.PontoDAO;
import databit.com.br.datamobile.dao.ProdutoDAO;
import databit.com.br.datamobile.dao.ServicoDAO;
import databit.com.br.datamobile.dao.ServicoIncompletoDAO;
import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Composicao;
import databit.com.br.datamobile.domain.Condicao;
import databit.com.br.datamobile.domain.ConfigFTP;
import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.domain.Servico;
import databit.com.br.datamobile.domain.ServicoIncompleto;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.service.PontoService;
import databit.com.br.datamobile.service.SincronizacaoDatabit;
import databit.com.br.datamobile.service.SincronizacaoPendencia;
import databit.com.br.datamobile.wsclient.ComposicaoWSClient;
import databit.com.br.datamobile.wsclient.CondicaoWSClient;
import databit.com.br.datamobile.wsclient.ConfigFTPWSClient;
import databit.com.br.datamobile.wsclient.ConfigmobileWSClient;
import databit.com.br.datamobile.wsclient.ConfiguracaoWSClient;
import databit.com.br.datamobile.wsclient.OsWSClient;
import databit.com.br.datamobile.wsclient.PontoWSClient;
import databit.com.br.datamobile.wsclient.ProdutoWSClient;
import databit.com.br.datamobile.wsclient.ServicoIncompletoWSClient;
import databit.com.br.datamobile.wsclient.ServicoWSClient;
import databit.com.br.datamobile.wsclient.UsuarioWSClient;

public class SincronizaActivity extends AppCompatActivity {

    private Configuracao configuracao;
    private ConfiguracaoDAO configuracaoDAO;
    private boolean bSync;
    private boolean bAuto;
    private boolean bSomenteos;
    private boolean bSomenteponto;
    private String sOspendente;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincroniza);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);
        setSupportActionBar(toolbar);

        configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        final ImageView imgProduto = (ImageView) findViewById(R.id.imageProd);
        final TextView txtProduto = (TextView) findViewById(R.id.textProd);
        final ImageView imgComp = (ImageView) findViewById(R.id.imageComp);
        final TextView txtComp = (TextView) findViewById(R.id.textComp);
        final CheckBox checkProduto = (CheckBox) findViewById(R.id.checkProduto);
        checkProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (checkProduto.isChecked()) {
                   imgProduto.setVisibility(View.VISIBLE);
                   txtProduto.setVisibility(View.VISIBLE);
                   imgComp.setVisibility(View.VISIBLE);
                   txtComp.setVisibility(View.VISIBLE);
               }
               else {
                   imgProduto.setVisibility(View.INVISIBLE);
                   txtProduto.setVisibility(View.INVISIBLE);
                   imgComp.setVisibility(View.INVISIBLE);
                   txtComp.setVisibility(View.INVISIBLE);
               }
            }
        });

        TextView txtDatasync = (TextView) findViewById(R.id.textDatasync);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtDatasync.setText("Última Sincronização : " + dateFormat.format(configuracao.getDatasync()));

        sOspendente = "OSs Pendentes para Sincronização : ";
        OsDAO osDAO = new OsDAO();
        List<Os> listOs = osDAO.listarOs(" fechaok = 1 and (sync = 'N' or sync is null) ");
        OsComparator osComparator = new OsComparator();
        Collections.sort(listOs, osComparator);
        for (Os os : listOs) {
            sOspendente = sOspendente + os.getCodigo().toString()+", ";
        }
        TextView txtOs = (TextView) findViewById(R.id.textOs);
        txtOs.setText(sOspendente);

        bAuto = getIntent().getBooleanExtra("auto",false);
        bSomenteos = getIntent().getBooleanExtra("somenteos",false);
        bSomenteponto = getIntent().getBooleanExtra("somenteponto",false);


        if (bAuto) {
            usuario = (Usuario) getIntent().getSerializableExtra("usuario");
            sincronizarUsuario();
            sincronizarCondicao();
            sincronizarServicoIncompleto();
            sincronizarServico();
            sincronizarConfig();
            sincronizarConfigMobile();
            sincronizarConfigFTP();
            sincronizarPontoenv();
            sincronizarPonto();
            sincronizarInformacao();
        }

        LinearLayout lnUsuario = (LinearLayout) findViewById(R.id.lnUsuario);
        LinearLayout lnCondicao = (LinearLayout) findViewById(R.id.lnCondicao);
        LinearLayout lnServicoimp = (LinearLayout) findViewById(R.id.lnServicoimp);
        LinearLayout lnServico = (LinearLayout) findViewById(R.id.lnServico);
        LinearLayout lnDispositivo = (LinearLayout) findViewById(R.id.lnDispositivo);
        LinearLayout lnFtp = (LinearLayout) findViewById(R.id.lnFtp);
        LinearLayout lnPonto = (LinearLayout) findViewById(R.id.lnPonto);
        LinearLayout lnPontoenv = (LinearLayout) findViewById(R.id.lnPontoenv);
        LinearLayout lnOs = (LinearLayout) findViewById(R.id.lnOs);
        LinearLayout lnOs2 = (LinearLayout) findViewById(R.id.lnOs2);
        LinearLayout lnProdutos = (LinearLayout) findViewById(R.id.lnProdutos);
        LinearLayout lnConfig = (LinearLayout) findViewById(R.id.lnConfig);
        LinearLayout lnCadservicos = (LinearLayout) findViewById(R.id.lnCadservicos);
        if (bSomenteos) {
            lnUsuario.setVisibility(View.GONE);
            lnCondicao.setVisibility(View.GONE);
            lnServicoimp.setVisibility(View.GONE);
            lnServico.setVisibility(View.GONE);
            lnDispositivo.setVisibility(View.GONE);
            lnFtp.setVisibility(View.GONE);
            lnPonto.setVisibility(View.GONE);
            lnPontoenv.setVisibility(View.GONE);
            lnProdutos.setVisibility(View.GONE);
            lnCadservicos.setVisibility(View.GONE);
            lnConfig.setVisibility(View.GONE);
            checkProduto.setVisibility(View.GONE);
        }
        if (bSomenteponto) {
            lnUsuario.setVisibility(View.GONE);
            lnCondicao.setVisibility(View.GONE);
            lnServicoimp.setVisibility(View.GONE);
            lnServico.setVisibility(View.GONE);
            lnDispositivo.setVisibility(View.GONE);
            lnFtp.setVisibility(View.GONE);
            lnPonto.setVisibility(View.GONE);
            lnOs.setVisibility(View.GONE);
            lnOs2.setVisibility(View.GONE);
            lnProdutos.setVisibility(View.GONE);
            lnCadservicos.setVisibility(View.GONE);
            lnConfig.setVisibility(View.GONE);
            checkProduto.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean bNovo = getIntent().getBooleanExtra("novo",false);
        if ((bNovo == true) && (bSync == false)) {
            configuracaoDAO.excluiConfiguracao(configuracao);
        }
        else {
            if (bSync == true) {
                Date dt = new Date();
                configuracao.setDatasync(dt);
                configuracaoDAO.gravaConfiguracao(configuracao);
            }
            if ((bNovo == true) || (bAuto == true)) {
                Intent intent = new Intent(SincronizaActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_sincroniza_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_sync: {
                 if (!(bSomenteos) && !(bSomenteponto)) {
                     CheckBox checkProduto = (CheckBox) findViewById(R.id.checkProduto);
                     sincronizarUsuario();
                     sincronizarCondicao();
                     sincronizarServicoIncompleto();
                     sincronizarServico();
                     sincronizarConfig();
                     sincronizarConfigMobile();
                     sincronizarConfigFTP();
                     sincronizarPonto();
                     if (checkProduto.isChecked()) {
                         sincronizarProduto();
                         sincronizarComposicao();
                     }
                }
                if (!(bSomenteponto)) {
                    sincronizarInformacao();
                }
                if (!(bSomenteos)) {
                    sincronizarPontoenv();
                }

                bSync = true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void sincronizarUsuario() {
        SincUsuarioAsyncTask task = new SincUsuarioAsyncTask();
        task.execute();
    }


    class SincUsuarioAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegUsuario;
        private boolean bErro = false;
        private TextView txtUsuario = (TextView) findViewById(R.id.textUsuario);
        private ImageView imgUsuario = (ImageView) findViewById(R.id.imageUsuario);
        private String sMensagem = "";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgUsuario.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegUsuario=0;
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    UsuarioWSClient wsClientusuario = new UsuarioWSClient();
                    List<Usuario> listUsuario = wsClientusuario.buscaUsuario();

                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    for (Usuario usuario : listUsuario) {
                        usuarioDAO.createOrUpdate(usuario);
                    }
                    iRegUsuario = listUsuario.size();
                }
                else {
                    bErro = true;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtUsuario.setText(iRegUsuario + " Usuarios Sincronizados!");
                imgUsuario.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtUsuario.setText("ERRO - Usuarios, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgUsuario.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }



    private void sincronizarCondicao() {
        SincCondicaoAsyncTask task = new SincCondicaoAsyncTask();
        task.execute();
    }

    class SincCondicaoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegCondicao;
        private boolean bErro = false;
        private TextView txtCondicao = (TextView) findViewById(R.id.textCondicao);
        private ImageView imgCondicao = (ImageView) findViewById(R.id.imageCondicao);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgCondicao.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Condição de Intervenção
            iRegCondicao = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    CondicaoDAO condicaoDAO = new CondicaoDAO();
                    CondicaoWSClient wsClient = new CondicaoWSClient();
                    List<Condicao> listCondicao = wsClient.buscaCondicao();
                    List<Condicao> listCondicaoant = condicaoDAO.listarCondicao("0=0");
                    if (listCondicaoant.size() > 0) {
                        for (Condicao condicao : listCondicaoant) {
                            condicaoDAO.delete(condicao);
                        }
                    }
                    for (Condicao condicao : listCondicao) {
                        condicaoDAO.createOrUpdate(condicao);
                    }
                    iRegCondicao = listCondicao.size();
                }
                else {
                    bErro = true;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtCondicao.setText(iRegCondicao + " Condições Sincronizadas!");
                imgCondicao.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtCondicao.setText("ERRO - Condições, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgCondicao.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }
    }


    private void sincronizarServicoIncompleto() {
        SincServicoIncompletoAsyncTask task = new SincServicoIncompletoAsyncTask();
        task.execute();
    }

    class SincServicoIncompletoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegServicoIncompleto;
        private boolean bErro = false;
        private TextView txtServicoIncompleto = (TextView) findViewById(R.id.textIncompleto);
        private ImageView imgServicoIncompleto = (ImageView) findViewById(R.id.imageIncompleto);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgServicoIncompleto.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // ServicoIncompletos
            iRegServicoIncompleto = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ServicoIncompletoWSClient wsClient = new ServicoIncompletoWSClient();
                    ServicoIncompletoDAO ServicoIncompletoDAO = new ServicoIncompletoDAO();
                    List<ServicoIncompleto> listServicoIncompleto = wsClient.buscaServicoIncompleto();
                    List<ServicoIncompleto> listServicoIncompletoant = ServicoIncompletoDAO.listarServicoIncompleto("0=0");
                    if (listServicoIncompletoant.size() > 0) {
                        for (ServicoIncompleto servicoIncompleto : listServicoIncompletoant) {
                            ServicoIncompletoDAO.delete(servicoIncompleto);
                        }
                    }
                    for (ServicoIncompleto servicoIncompleto : listServicoIncompleto) {
                        ServicoIncompletoDAO.createOrUpdate(servicoIncompleto);
                    }
                    iRegServicoIncompleto = listServicoIncompleto.size();
                }
                else {
                    bErro = true;
                }


            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtServicoIncompleto.setText(iRegServicoIncompleto + " Serviços Incompletos Sincronizados!");
                imgServicoIncompleto.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtServicoIncompleto.setText("ERRO - Serviços Incompletos, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgServicoIncompleto.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }


    private void sincronizarInformacao() {
        SincInformacaoAsyncTask task = new SincInformacaoAsyncTask();
        task.execute();
    }

    class SincInformacaoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegInfo;
        private boolean bErro = false;
        private TextView txtInfo = (TextView) findViewById(R.id.textInfo);
        private ImageView imgInfo = (ImageView) findViewById(R.id.imageInfo);
        private String sResult = "";
        private String sOk = "";
        private String sErro = "";




        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgInfo.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //  Infor
            iRegInfo = 0;
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    OsWSClient wsClient = new OsWSClient();
                    OsDAO osDAO = new OsDAO();
                    List<Os> listOs = osDAO.listarOs(" fechaok = 1 and (sync = 'N' or sync is null)");
                    OsComparator osComparator = new OsComparator();
                    Collections.sort(listOs, osComparator);
                    for (Os os : listOs) {
                        Thread.sleep(1000);
                        sResult = wsClient.enviaOS(os);
                        if (sResult.equals("OK")) {
                            if (os.getStatus_check() == 4) {
                                try {
                                    os.setSync("S");
                                    osDAO.gravaOs(os);
                                    sOk = sOk + os.getCodigo().toString() + ", ";
                                } catch (Throwable t) {
                                    sErro = sErro + os.getCodigo().toString() + ", ";
                                    bErro = true;
                                    sResult = t.getMessage();
                                    t.printStackTrace();
                                }
                            }
                        }
                        else {
                            sErro = sErro + os.getCodigo().toString() + ", ";
                            bErro = true;
                        }

                    }
                    iRegInfo = listOs.size();
                }
                else {
                    bErro = true;
                }
            } catch (Throwable t) {
                bErro = true;
                sResult = t.getMessage();
                t.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtInfo.setText(iRegInfo + " Informações Sincronizadas! OS: " + sOk);
                imgInfo.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtInfo.setText("ERRO - OS: " + sErro +" Mensagem : " + sResult);
                imgInfo.setImageResource(R.mipmap.ic_osatrasado);
            }

            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }



    private void sincronizarProduto() {
        SincProdutoAsyncTask task = new SincProdutoAsyncTask();
        task.execute();
    }

    class SincProdutoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegProduto;
        private boolean bErro = false;
        private TextView txtProduto = (TextView) findViewById(R.id.textProd);
        private ImageView imgProduto = (ImageView) findViewById(R.id.imageProd);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgProduto.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegProduto = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    ProdutoWSClient wsClient = new ProdutoWSClient();
                    List<Produto> listProdutoant = produtoDAO.allProduto();
                    if (listProdutoant.size() > 0) {
                        for (Produto produto : listProdutoant) {
                            produtoDAO.delete(produto);
                        }
                    }
                    List<Produto> listProduto = wsClient.buscaProduto();
                    for (Produto produto : listProduto) {
                        produtoDAO.createOrUpdate(produto);
                    }
                    iRegProduto = listProduto.size();
                }
                else {
                    bErro = true;
                }


            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtProduto.setText(iRegProduto + " Produtos Sincronizados!");
                imgProduto.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtProduto.setText("ERRO - Produtos, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgProduto.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }

    private void sincronizarServico() {
        SincServicoAsyncTask task = new SincServicoAsyncTask();
        task.execute();
    }

    class SincServicoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegServico;
        private boolean bErro = false;
        private TextView txtServico = (TextView) findViewById(R.id.textServico);
        private ImageView imgServico = (ImageView) findViewById(R.id.imageServico);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgServico.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegServico = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ServicoWSClient wsClient = new ServicoWSClient();
                    ServicoDAO servicoDAO = new ServicoDAO();
                    List<Servico> listServicoant = servicoDAO.allServico();
                    if (listServicoant.size() > 0) {
                        for (Servico servico : listServicoant) {
                            servicoDAO.delete(servico);
                        }
                    }
                    List<Servico> listServico = wsClient.buscaServico();
                    for (Servico servico : listServico) {
                        servicoDAO.createOrUpdate(servico);
                    }
                    iRegServico = listServico.size();
                }
                else {
                    bErro = true;
                }


            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtServico.setText(iRegServico + " Servicos Sincronizados!");
                imgServico.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtServico.setText("ERRO - Servicos, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgServico.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }

    private void sincronizarComposicao() {
        SincComposicaoAsyncTask task = new SincComposicaoAsyncTask();
        task.execute();
    }

    class SincComposicaoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegComposicao;
        private boolean bErro = false;
        private TextView txtComposicao = (TextView) findViewById(R.id.textComp);
        private ImageView imgComposicao = (ImageView) findViewById(R.id.imageComp);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgComposicao.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegComposicao = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ComposicaoWSClient wsClient = new ComposicaoWSClient();
                    ComposicaoDAO composicaoDAO = new ComposicaoDAO();
                    List<Composicao> listComposicaoant = composicaoDAO.allComposicao();
                    if (listComposicaoant.size() > 0) {
                        for (Composicao composicao : listComposicaoant) {
                            composicaoDAO.delete(composicao);
                        }
                    }
                    List<Composicao> listComposicao = wsClient.buscaComposicao();
                    for (Composicao composicao : listComposicao) {
                        composicaoDAO.createOrUpdate(composicao);
                    }
                    iRegComposicao = listComposicao.size();
                }
                else {
                    bErro = true;
                }


            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtComposicao.setText(iRegComposicao + " Composições Sincronizadas!");
                imgComposicao.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtComposicao.setText("ERRO - Composições, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgComposicao.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }

    private void sincronizarPonto() {
        SincPontoAsyncTask task = new SincPontoAsyncTask();
        task.execute();
    }

    class SincPontoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegPonto;
        private boolean bErro = false;
        private TextView txtPonto = (TextView) findViewById(R.id.textPonto);
        private ImageView imgPonto = (ImageView) findViewById(R.id.imagePonto);
        private String sMensagem = "";


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgPonto.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegPonto = 0;

            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    PontoWSClient wsClient = new PontoWSClient();
                    List<Ponto> listPonto = wsClient.buscaPonto();
                    PontoDAO pontoDAO = new PontoDAO();
                    List<Ponto> listPontoant = pontoDAO.listarPonto("integra <> 'N'");
                    if (listPontoant.size() > 0) {
                        for (Ponto ponto : listPontoant) {
                            pontoDAO.delete(ponto);
                        }
                    }
                    for (Ponto ponto : listPonto) {
                        pontoDAO.createOrUpdate(ponto);
                    }
                    iRegPonto = listPonto.size();
                }
                else {
                    bErro = true;
                }


            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtPonto.setText(iRegPonto + " Registros de Ponto Sincronizados!");
                imgPonto.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtPonto.setText("ERRO - Ponto, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgPonto.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }


        }
    }

    private void sincronizarConfig() {
        SincConfigAsyncTask task = new SincConfigAsyncTask();
        task.execute();
    }

    class SincConfigAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegOs;
        private Boolean bErro = false;
        private String sMensagem = "";
        private Configuracao configuracao;
        private TextView txtConfig = (TextView) findViewById(R.id.textConfiggeral);
        private ImageView imgConfig = (ImageView) findViewById(R.id.imageConfiggeral);

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgConfig.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegOs = 0;
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
                    configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
                    ConfiguracaoWSClient configuracaoWSClient = new ConfiguracaoWSClient();
                    String sResult = configuracaoWSClient.recebeConfiguracao(configuracao);
                    configuracao.setMaischeckin(sResult.substring(0,1));
                    configuracao.setObrkm(sResult.substring(2, 3));
                    configuracao.setTipolan(sResult.substring(4, 5));
                    configuracao.setTemposinc(Integer.parseInt(sResult.substring(7, 10)));
                    configuracao.setTemposmens(Integer.parseInt(sResult.substring(12, 15)));
                    configuracao.setReq(sResult.substring(16, 17));
                    configuracao.setRecolha(sResult.substring(18, 19));
                    configuracao.setPonto(sResult.substring(20, 21));
                    configuracao.setIntervalo(Integer.parseInt(sResult.substring(22, 26)));
                    configuracao.setHoraintervalo(sResult.substring(27, 32));
                    configuracao.setHorafinal(sResult.substring(33, 38));
                    configuracao.setHorainimanha(sResult.substring(39, 44));
                    configuracao.setHorafimmanha(sResult.substring(45, 50));
                    configuracao.setHorainitarde(sResult.substring(51, 56));
                    configuracao.setHorafimtarde(sResult.substring(57, 62));
                    configuracaoDAO.gravaConfiguracao(configuracao);
                    startService(new Intent(getApplicationContext(), SincronizacaoDatabit.class));
                    startService(new Intent(getApplicationContext(), SincronizacaoPendencia.class));
                    if (configuracao.getPonto().equals("S")) {
                        startService(new Intent(getApplicationContext(), PontoService.class));
                    }
                    bErro = false;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtConfig.setText("Configurações gerais sincronizadas com Sucesso!");
                imgConfig.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtConfig.setText("ERRO - Config, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgConfig.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }
    }

    private void sincronizarConfigMobile() {
        SincConfigMobileAsyncTask task = new SincConfigMobileAsyncTask();
        task.execute();
    }

    class SincConfigMobileAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Boolean bErro = false;
        private String sMensagem = "";
        private TextView txtConfig = (TextView) findViewById(R.id.textConfigdisp);
        private ImageView imgConfig = (ImageView) findViewById(R.id.imageConfigdisp);

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgConfig.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ConfigmobileDAO configmobileDAO = new ConfigmobileDAO();
                    ConfigmobileWSClient configmobileWSClient = new ConfigmobileWSClient();
                    List<Configmobile> listConfigmobile = configmobileWSClient.recebeConfigmobile();
                    for (Configmobile configmobile : listConfigmobile) {
                        configmobileDAO.createOrUpdate(configmobile);
                    }
                    bErro = false;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtConfig.setText("Configurações do dispositivo sincronizadas com Sucesso!");
                imgConfig.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtConfig.setText("ERRO - Config, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgConfig.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }
    }

    private void sincronizarConfigFTP() {
        SincConfigFTPAsyncTask task = new SincConfigFTPAsyncTask();
        task.execute();
    }

    class SincConfigFTPAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Boolean bErro = false;
        private String sMensagem = "";
        private TextView txtConfig = (TextView) findViewById(R.id.textConfigftp);
        private ImageView imgConfig = (ImageView) findViewById(R.id.imageConfigftp);

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgConfig.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    ConfigFTPDAO ConfigFTPDAO = new ConfigFTPDAO();
                    ConfigFTPWSClient ConfigFTPWSClient = new ConfigFTPWSClient();
                    List<ConfigFTP> listConfigFTP = ConfigFTPWSClient.recebeFtp();
                    for (ConfigFTP ConfigFTP : listConfigFTP) {
                        ConfigFTPDAO.createOrUpdate(ConfigFTP);
                    }
                    bErro = false;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtConfig.setText("Configurações do ftp sincronizadas com Sucesso!");
                imgConfig.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtConfig.setText("ERRO - Config, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgConfig.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }
    }


    private void sincronizarPontoenv() {
        SincPontoenvAsyncTask task = new SincPontoenvAsyncTask();
        task.execute();
    }


    class SincPontoenvAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private Integer iRegPonto;
        private Integer iRegErro;
        private boolean bErro = false;
        private TextView txtPontoenv = (TextView) findViewById(R.id.textPontoenv);
        private ImageView imgPontoenv = (ImageView) findViewById(R.id.imagePontoenv);
        private String sMensagem = "";
        private String sRetorno = "";
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SincronizaActivity.this, null, "Sincronizando Dados");
            imgPontoenv.setImageResource(R.mipmap.ic_osexecucao);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            iRegPonto=0;
            iRegErro=0;
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    PontoWSClient pontoWSClient = new PontoWSClient();
                    PontoDAO pontoDAO = new PontoDAO();
                    List<Ponto> listPonto = pontoDAO.listarPonto(" integra = 'N' and integra is not null ");
                    for (Ponto ponto : listPonto) {
                        sRetorno = pontoWSClient.enviaPonto(ponto);
                        if (sRetorno.equals("OK")) {
                            ponto.setIntegra("S");
                            pontoDAO.gravaPonto(ponto);
                            iRegPonto = iRegPonto + 1;
                        }
                        else {
                            ponto.setIntegra("N");
                            pontoDAO.gravaPonto(ponto);
                            iRegErro = iRegErro + 1;
                        }
                    }
                }
                else {
                    bErro = true;
                }
            } catch (Throwable t) {
                bErro = true;
                sMensagem = t.getMessage();
                t.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                txtPontoenv.setText(iRegPonto + " Registros de Ponto Sincronizados, " + iRegErro.toString()+" erros !");
                imgPontoenv.setImageResource(R.mipmap.ic_osconcluido);
            }
            else {
                txtPontoenv.setText("ERRO - Ponto, não foi possivel realizar a operação, o dispositivo esta fora da Internet ou o endereço Webservice está indisponível ! "+sMensagem);
                imgPontoenv.setImageResource(R.mipmap.ic_osatrasado);
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


}



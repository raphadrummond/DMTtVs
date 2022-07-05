package databit.com.br.datamobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.wsclient.DatabitWSClient;

public class ConfigActivity extends AppCompatActivity {

    private Button btnGravar;
    private Configuracao configuracao;
    private ConfiguracaoDAO configuracaoDAO;
    private EditText edtCNPJ;
    private EditText edtDatabit;
    private EditText edtWebService;
    private EditText edtIp;
    private Boolean bNovo;
    private boolean bErro = false;
    private String sResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);
        setSupportActionBar(toolbar);

        configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        edtCNPJ = (EditText) findViewById(R.id.editCNPJ);
        edtDatabit = (EditText) findViewById(R.id.editDatabit);
        edtWebService = (EditText) findViewById(R.id.editWebService);
        edtIp = (EditText) findViewById(R.id.editIp);

        bNovo = (configuracao == null);
        if (configuracao != null) {
            edtCNPJ.setText(configuracao.getCnpj());
            edtDatabit.setText(configuracao.getCoddatabit());
            edtWebService.setText(configuracao.getWebservice());
            edtIp.setText(configuracao.getIp());
        }

        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setText("Gravar");
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtCNPJ.getText().toString().equals("")) {
                    Toast.makeText(ConfigActivity.this, "Campo CNPJ é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtCNPJ.requestFocus();
                    return;
                }
                if (edtDatabit.getText().toString().equals("")) {
                    Toast.makeText(ConfigActivity.this, "Código DATABIT é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtDatabit.requestFocus();
                    return;
                }
                if (edtWebService.getText().toString().equals("")) {
                    Toast.makeText(ConfigActivity.this, "Endereço WebService é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtWebService.requestFocus();
                    return;
                }
                if (edtIp.getText().toString().equals("")) {
                    Toast.makeText(ConfigActivity.this, "Endereço IP é de preenchimento Obrigatório !", Toast.LENGTH_SHORT).show();
                    edtIp.requestFocus();
                    return;
                }

                if (configuracao == null) {
                    configuracao = new Configuracao();
                    String mytime = "01/01/2010";
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date myDate = null;
                    try {
                        myDate = dateFormat.parse(mytime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    configuracao.setDatasync(myDate);
                }
                configuracao.setId(1);
                configuracao.setCnpj(edtCNPJ.getText().toString());
                configuracao.setCoddatabit(edtDatabit.getText().toString());
                configuracao.setWebservice(edtWebService.getText().toString());
                configuracao.setIp(edtIp.getText().toString());
                configuracaoDAO.gravaConfiguracao(configuracao);
                try {
                    autorizacao();
                 } catch (Throwable t) {
                    //configuracaoDAO.excluiConfiguracao(configuracao);
                    t.printStackTrace();
                }


            }
        });

    }

    private void autorizacao() {
        AutorizacaoAsyncTask task = new AutorizacaoAsyncTask();
        task.execute();
    }

    class AutorizacaoAsyncTask extends AsyncTask<Void, Void,Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult;
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ConfigActivity.this, null, "Verificando Autorização da DATABIT");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult="";
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    DatabitWSClient databitWSClient = new DatabitWSClient();
                    sResult = databitWSClient.Autorizacao(configuracao);
                    if (sResult.equals("OK")) {
                        bErro = false;
                    }
                    else {
                        bErro = true;
                        sErro = sResult;
                    }
                }

            } catch (Throwable t) {
                bErro = true;
                sErro = t.getMessage().toString();
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (bErro == false) {
                Toast.makeText(ConfigActivity.this, "Cliente Autorizado pela DATABIT !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConfigActivity.this, SincronizaActivity.class);
                intent.putExtra("novo",bNovo);
                startActivity(intent);
                finish();
            }
            else {
                configuracaoDAO.excluiConfiguracao(configuracao);
                Toast.makeText(ConfigActivity.this, "Não é possível realizar autorização: "+sErro, Toast.LENGTH_SHORT).show();
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                configuracaoDAO.excluiConfiguracao(configuracao);
                t.printStackTrace();
            }
        }
    }





}

package databit.com.br.datamobile.activity;

import static databit.com.br.datamobile.infra.SerialNumber.getSerialNumber;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import databit.com.br.datamobile.BuildConfig;
import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.AparelhoDAO;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Localizacao;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.gcm.RegistrationIntentService;
import databit.com.br.datamobile.infra.Internet;
import databit.com.br.datamobile.location.Localizador;
import databit.com.br.datamobile.service.SincronizacaoLocalidade;
import databit.com.br.datamobile.service.SincronizacaoPendencia;
import databit.com.br.datamobile.wsclient.AparelhoWSClient;
import databit.com.br.datamobile.wsclient.DatabitWSClient;

public class LoginActivity extends AppCompatActivity {

    private Button btnSair;
    private Button btnEntrar;
    private Button btnConfig;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private EditText edtUsuario;
    private EditText edtSenha;
    private Aparelho aparelho;
    private ConfiguracaoDAO configuracaoDAO;
    private Configuracao configuracao;
    private AparelhoDAO aparelhoDAO;
    private Localizacao localizacaoatual;
    private CheckBox chSincronizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_dataservice);
        setSupportActionBar(toolbar);

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        btnSair = (Button) findViewById(R.id.btnSair);
        btnSair.setText("Sair");
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfig = (Button) findViewById(R.id.btnConfig);
        btnConfig.setText("Config");
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent = new Intent(LoginActivity.this, ConfigActivity.class);
           startActivity(intent);
            }
        });

        configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        aparelhoDAO = new AparelhoDAO();
        aparelho = aparelhoDAO.procuraAparelho("id = 1");

        localizacaoatual = new Localizacao();
        verificaPermissaoLocalizacao();

        edtUsuario = (EditText) findViewById(R.id.editTextUsuario);
        edtSenha = (EditText) findViewById(R.id.editTextSenha);
        chSincronizar = (CheckBox) findViewById(R.id.chSincronizar);

        if (aparelho != null) {
            if (aparelho.getLogin() != null) {
                edtUsuario.setText(aparelho.getLogin().toString());
            }
        }

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setText("Entrar");
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((edtUsuario.getText().toString() == "") || (edtUsuario.getText() == null)) {
                    Toast.makeText(LoginActivity.this, "Usuario é de Preenchimento Obrigatório", Toast.LENGTH_SHORT).show();
                    edtUsuario.requestFocus();
                    return;
                }
                if ((edtSenha.getText().toString() == "") || (edtSenha.getText() == null)) {
                    Toast.makeText(LoginActivity.this, "Senha é de Preenchimento Obrigatório", Toast.LENGTH_SHORT).show();
                    edtSenha.requestFocus();
                    return;
                }


                usuarioDAO = new UsuarioDAO();
                usuario = usuarioDAO.procuraUsuario("login = '" + edtUsuario.getText().toString() + "' ");


                if (usuario != null) {
                    if (!usuario.getSenha().toString().equals(edtSenha.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Senha Inválida !", Toast.LENGTH_SHORT).show();
                        edtSenha.requestFocus();
                        return;
                    }
                }

                if (usuario == null) {
                    Toast.makeText(LoginActivity.this, "Usuario inexistente !", Toast.LENGTH_SHORT).show();
                    edtUsuario.requestFocus();
                    return;
                } else {
                    if (aparelho == null) {
                        aparelho = new Aparelho();
                        aparelho.setId(1);
                    }
                    Date data = new Date();
                    aparelho.setUsuario(usuario.getNome().toString());
                    aparelho.setLogin(usuario.getLogin().toString());
                    aparelho.setSerial(getSerialNumber());
                    aparelho.setVersao(android.os.Build.VERSION.RELEASE);
                    aparelho.setModelo(Build.MODEL);
                    aparelho.setFabricante(Build.MANUFACTURER);
                    aparelho.setVersaocode(BuildConfig.VERSION_CODE);
                    aparelho.setVersaoname(BuildConfig.VERSION_NAME);
                    aparelho.setCoddatabit(configuracao.getCoddatabit().toString());
                    aparelho.setLatitude(localizacaoatual.getLatitude());
                    aparelho.setLongitude(localizacaoatual.getLongitude());
                    aparelho.setData(data);
                    aparelhoDAO.gravaAparelho(aparelho);
                    autorizacao();
                    dispositivo();
                    localidade();
                    startService(new Intent(getApplicationContext(), SincronizacaoLocalidade.class));
                    startService(new Intent(getApplicationContext(), SincronizacaoPendencia.class));
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("nomeusuario", usuario.getNome().toString());
                    intent.putExtra("empresa", usuario.getNomeempresa().toString());
                    if (usuario.getEmail() != null) {
                        intent.putExtra("email", usuario.getEmail().toString());
                    }
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("latitudeatual",localizacaoatual.getLatitude());
                    intent.putExtra("longitudeatual",localizacaoatual.getLongitude());
                    intent.putExtra("endereco", localizacaoatual.getLogradouro());
                    intent.putExtra("sincauto", chSincronizar.isChecked());
                    startActivity(intent);
                    finish();
                }
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if (density == DisplayMetrics.DENSITY_HIGH) {
            Toast.makeText(this, "DENSITY_HIGH (hdpi) ... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            Toast.makeText(this, "DENSITY_MEDIUM (mdpi)... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_LOW) {
            Toast.makeText(this, "DENSITY_LOW (ldpi)... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XHIGH) {
            Toast.makeText(this, "DENSITY_XHIGH... (xhdpi) Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XXHIGH) {
            Toast.makeText(this, "DENSITY_XXHIGH (xxhpi)... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        } else if (density == DisplayMetrics.DENSITY_XXXHIGH) {
            Toast.makeText(this, "DENSITY_XXXHIGH (xxxhpi)... Density is " + String.valueOf(density), Toast.LENGTH_LONG).show();
        }

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen", Toast.LENGTH_LONG).show();
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen", Toast.LENGTH_LONG).show();
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(this, "XLarge sized screen", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show();
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


    }

    private void dispositivo() {
        DispositivoAsyncTask task = new DispositivoAsyncTask();
        task.execute();
    }

    class DispositivoAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "OK";
        private String sErro;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, null, "Atualizando Dados do Dispositivo");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult = "";
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    DatabitWSClient databitWSClient = new DatabitWSClient();
                    sResult = databitWSClient.enviaAparelho(aparelho);
                    if (sResult.equals("OK")) {
                        bErro = false;
                    } else {
                        bErro = true;
                        sErro = sResult;
                    }
                    Thread.sleep(1000);
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
                Toast.makeText(LoginActivity.this, "Dispositivo Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                if (sErro.equals("RESTRITO")) {
                    Toast.makeText(LoginActivity.this, "Atenção, este dispositivo não esta autorizado para usar este aplicativo !", Toast.LENGTH_LONG).show();
                    configuracaoDAO.excluiConfiguracao(configuracao);
                    Intent i = new Intent(LoginActivity.this, ConfigActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Problemas de Atualização do Dispositivo: " + sErro, Toast.LENGTH_SHORT).show();
                }
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void localidade() {
        LocalidadeAsyncTask task = new LocalidadeAsyncTask();
        task.execute();
    }

    class LocalidadeAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "OK";
        private String sErro;
        private Boolean bSemhoraauto = false;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, null, "Atualizando Localidade do Dispositivo");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult = "";
            try {
                bSemhoraauto=(Confighora() == 0);
                if (bSemhoraauto == false) {
                    if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                        AparelhoWSClient aparelhoWSClient = new AparelhoWSClient();
                        sResult = aparelhoWSClient.enviaLocalidade(aparelho);
                        if (sResult.equals("OK")) {
                            bErro = false;
                        } else {
                            bErro = true;
                            sErro = sResult;
                        }
                        Thread.sleep(1000);
                    }
                }
                else {
                    bErro = true;
                    bSemhoraauto = true;
                    sErro = "Não foi possível acessar a localidade, verifique se a HORA do dispositivo está automaticamente com o horário da rede, reiniciar o aplicativo, e tentar novamente !";
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
                Toast.makeText(LoginActivity.this, "Localidade Atualizada com Sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Problemas de Atualização da Localidade: " + sErro, Toast.LENGTH_LONG).show();
                if (bSemhoraauto == true) {
                    startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                }
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }



    private void autorizacao() {
        AutorizacaoAsyncTask task = new AutorizacaoAsyncTask();
        task.execute();
    }

    class AutorizacaoAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;
        private boolean bErro = false;
        private String sResult = "OK";
        private String sErro;
        private Boolean bErroInternet = false;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, null, "Verificando Autorização da DATABIT");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            sResult = "";
            try {
                if ((Internet.isDeviceOnline(getBaseContext())) && (Internet.urlOnline(getBaseContext()))) {
                    DatabitWSClient databitWSClient = new DatabitWSClient();
                    sResult = databitWSClient.Autorizacao(configuracao);
                    if (sResult.equals("OK")) {
                        bErro = false;
                    } else {
                        bErro = true;
                        sErro = sResult;
                        bErroInternet = false;
                    }
                    Thread.sleep(1000);
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
                Toast.makeText(LoginActivity.this, "Cliente Autorizado pela DATABIT !", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(LoginActivity.this, "Não é possível realizar autorização: " + sErro, Toast.LENGTH_SHORT).show();
                if ((bErroInternet == false) && (sErro.equals("Cliente nao encontrado na DATABIT !") || sErro.equals("Cliente nao autorizado pela DATABIT! "))) {
                    configuracaoDAO.excluiConfiguracao(configuracao);
                    Intent i = new Intent(LoginActivity.this, ConfigActivity.class);
                    startActivity(i);
                }
            }
            try {
                progressDialog.dismiss();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void verificaPermissaoLocalizacao() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);

        } else {
            localizacaoatual =  buscaLocalizacao(localizacaoatual);
        }
    }

    private Localizacao buscaLocalizacao(Localizacao localizacao) {
        if (!Localizador.getInstance(this, localizacao).isGpsAvaiable(this)) {
            Intent viewIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(viewIntent);
            Toast.makeText(LoginActivity.this, "GPS não habilitado", Toast.LENGTH_SHORT).show();
        }
        Localizador.getInstance(this, localizacao).requestLocation();
        return localizacao;
    }

    public int Confighora() {
        int type = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            type =  Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0);
        }else{
            type =  Settings.System.getInt(getContentResolver(), Settings.System.AUTO_TIME, 0);
        }
        return type;
    }


}

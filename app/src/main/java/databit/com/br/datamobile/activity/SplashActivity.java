package databit.com.br.datamobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;

/**
 * Created by Sidney on 23/02/2016.
 */
public class SplashActivity extends AppCompatActivity {

    private static int TEMPO_SPLASH = 3000;
    private Configuracao configuracao;
    private ConfiguracaoDAO configuracaoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            // Carrega imagem da LOGO
            @Override
            public void run() {
                //Este método será executado por 3 segundos
                // Antes de iniciar a MainActivity

                configuracaoDAO = new ConfiguracaoDAO();

                configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
                if (configuracao == null) {
                    Intent intent = new Intent(SplashActivity.this, ConfigActivity.class);
                    startActivity(intent);
                }
                configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
                if (configuracao != null) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                // Fecha o activity atual
                finish();
            }
        },TEMPO_SPLASH);
    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu){ return true;}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {return true;}

}

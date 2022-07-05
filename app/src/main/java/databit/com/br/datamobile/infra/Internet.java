package databit.com.br.datamobile.infra;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.wsbase.HttpClient;

/**
 * Created by Sidney on 16/05/2016.
 */
public class Internet extends HttpClient {

    public static boolean isDeviceOnline(Context pContext) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static boolean urlOnline(Context context) {
        Configuracao configuracao;
        ConfiguracaoDAO configuracaoDAO;
        configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        Boolean bRetorno = false;
        try {
            URL url = new URL(configuracao.getIp()); // http://www.seusite.com.br
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000);
            conn.setRequestMethod("GET");
            for (int i = 1; i < 2; i++) {
                conn.connect();
                bRetorno = (conn.getResponseCode() == 200);
                if (bRetorno == true) {
                    break;
                }
            }
        } catch (IOException e) {
            bRetorno = false;
            e.printStackTrace();
        }

        return bRetorno;
    }






}

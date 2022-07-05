package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.DefeitoDAO;
import databit.com.br.datamobile.domain.Defeito;
import databit.com.br.datamobile.wsclient.DefeitoWSClient;

public class DefeitoIntentService extends IntentService {

    public DefeitoIntentService() {
        super("DefeitoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            DefeitoWSClient wsClient = new DefeitoWSClient();
            List<Defeito> listDefeito = wsClient.buscaDefeito();

            DefeitoDAO defeitoDAO = new DefeitoDAO();
            for (Defeito defeito : listDefeito) {
                defeitoDAO.createOrUpdate(defeito);
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

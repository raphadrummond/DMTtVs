package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.CondicaoDAO;
import databit.com.br.datamobile.domain.Condicao;
import databit.com.br.datamobile.wsclient.CondicaoWSClient;

public class CondicaoIntentService extends IntentService {

    public CondicaoIntentService() {
        super("CondicaoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            CondicaoWSClient wsClient = new CondicaoWSClient();
            List<Condicao> listCondicao = wsClient.buscaCondicao();

            CondicaoDAO condicaoDAO = new CondicaoDAO();
            for (Condicao condicao : listCondicao) {
                condicaoDAO.createOrUpdate(condicao);
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

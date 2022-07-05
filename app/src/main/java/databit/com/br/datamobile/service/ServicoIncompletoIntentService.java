package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.ServicoIncompletoDAO;
import databit.com.br.datamobile.domain.ServicoIncompleto;
import databit.com.br.datamobile.wsclient.ServicoIncompletoWSClient;

/**
 * Created by Sidney on 16/05/2016.
 */
public class ServicoIncompletoIntentService extends IntentService {

    public ServicoIncompletoIntentService() {
        super("ServicoIncompletoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ServicoIncompletoWSClient wsClient = new ServicoIncompletoWSClient();
            List<ServicoIncompleto> listServicoIncompleto = wsClient.buscaServicoIncompleto();

            ServicoIncompletoDAO servicoIncompletoDAO = new ServicoIncompletoDAO();
            for (ServicoIncompleto servicoIncompleto : listServicoIncompleto) {
                servicoIncompletoDAO.createOrUpdate(servicoIncompleto);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}

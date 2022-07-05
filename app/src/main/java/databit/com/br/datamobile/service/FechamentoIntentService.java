package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.Collections;
import java.util.List;

import databit.com.br.datamobile.comparator.FechamentoComparator;
import databit.com.br.datamobile.dao.FechamentoDAO;
import databit.com.br.datamobile.domain.Fechamento;
import databit.com.br.datamobile.wsclient.FechamentoWSClient;

/**
 * Created by Sidney on 17/06/2016.
 */
public class FechamentoIntentService extends IntentService {

    private String sResult;
    public FechamentoIntentService() {
        super("FechamentoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        FechamentoWSClient wsClient = new FechamentoWSClient();
        FechamentoDAO fechamentoDAO = new FechamentoDAO();
        List<Fechamento> listFechamento = fechamentoDAO.listarFechamento(" 0 = 0 ");
        FechamentoComparator fechamentoComparator = new FechamentoComparator();
        Collections.sort(listFechamento, fechamentoComparator);
        for (Fechamento fechamento : listFechamento) {
            sResult = wsClient.enviaFechamento(fechamento);
            if (sResult.equals("OK")) {
                fechamento.setSync("S");
                fechamentoDAO.gravaFechamento(fechamento);
            }
        }

    }
}

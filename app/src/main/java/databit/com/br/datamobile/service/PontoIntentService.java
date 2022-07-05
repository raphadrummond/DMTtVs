package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.PontoDAO;
import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.wsclient.PontoWSClient;

public class PontoIntentService extends IntentService {

    public PontoIntentService() {
        super("PontoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String sRetorno = "";
            PontoWSClient pontoWSClient = new PontoWSClient();
            PontoDAO pontoDAO = new PontoDAO();
            List<Ponto> listPonto = pontoDAO.listarPonto(" integra = 'N' and integra is not null ");
            for (Ponto ponto : listPonto) {
                sRetorno = pontoWSClient.enviaPonto(ponto);
                if (sRetorno.equals("OK")) {
                    ponto.setIntegra("S");
                    pontoDAO.gravaPonto(ponto);
                }
                else {
                    ponto.setIntegra("N");
                    pontoDAO.gravaPonto(ponto);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

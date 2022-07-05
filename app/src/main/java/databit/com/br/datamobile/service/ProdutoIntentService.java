package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.ProdutoDAO;
import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.wsclient.ProdutoWSClient;

/**
 * Created by Sidney on 14/05/2016.
 */
public class ProdutoIntentService  extends IntentService {

    public ProdutoIntentService() {
        super("ProdutoIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ProdutoWSClient wsClient = new ProdutoWSClient();
            List<Produto> listProduto = wsClient.buscaProduto();

            ProdutoDAO produtoDAO = new ProdutoDAO();
            for (Produto produto : listProduto) {
                produtoDAO.createOrUpdate(produto);
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

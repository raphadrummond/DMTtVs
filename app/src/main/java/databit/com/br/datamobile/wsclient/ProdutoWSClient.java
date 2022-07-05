package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Produto;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 14/05/2016.
 */
public class ProdutoWSClient extends AndroidWsClient {

    public List<Produto> buscaProduto() {

        String json = wsGet("recebeProduto");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Produto>>() {}.getType();
        List<Produto> retorno = gson.fromJson(json, collectionType);
        return retorno;

    }


}

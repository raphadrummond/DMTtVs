package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Item;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 04/04/2018.
 */
public class ItemWSClient extends AndroidWsClient {

    public Os os;

    public List<Item> buscaItem() {

        Integer iTipo = os.getOperacaomobile() - 1;

        String json = wsGet("recebeItens/"+os.getCodigo().toString()+"/"+os.getBanco().toString()+"/"+iTipo.toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Item>>() {}.getType();
        List<Item> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }

}

package databit.com.br.datamobile.wsclient;

import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Trocada;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 13/05/2016.
 */
public class TrocadaWSClient extends AndroidWsClient {

    public Os os;

    public List<Trocada> buscaTrocada() {

        String json = wsGet("recebeTrocada/"+os.getNumserie().toString()+"/"+os.getBanco().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Trocada>>() {}.getType();
        List<Trocada> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }

}

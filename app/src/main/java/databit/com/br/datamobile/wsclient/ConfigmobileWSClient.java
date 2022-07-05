package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Configmobile;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 28/03/2018.
 */
public class ConfigmobileWSClient extends AndroidWsClient {

    public List<Configmobile> recebeConfigmobile() {
        String json = wsGet("Configmobile");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Configmobile>>() {}.getType();
        List<Configmobile> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }


}

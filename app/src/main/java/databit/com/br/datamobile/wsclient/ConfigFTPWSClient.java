package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.ConfigFTP;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by Sidney on 28/03/2018.
 */
public class ConfigFTPWSClient extends AndroidWsClient {
    public List<ConfigFTP> recebeFtp() {
        String json = wsGet("ConfigFTP");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<ConfigFTP>>() {}.getType();
        List<ConfigFTP> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }

}

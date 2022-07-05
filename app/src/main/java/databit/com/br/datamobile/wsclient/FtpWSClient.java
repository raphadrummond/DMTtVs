package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Ftp;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by Sidney on 28/03/2018.
 */
public class FtpWSClient  extends AndroidWsClient {
    public List<Ftp> recebeFtp() {
        String json = wsGet("ConfigFTP");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Ftp>>() {}.getType();
        List<Ftp> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }

}

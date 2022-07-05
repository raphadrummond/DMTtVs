package databit.com.br.datamobile.wsclient;

import java.util.List;

import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.OsDefeito;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 12/05/2016.
 */
public class OsDefeitoWSClient extends AndroidWsClient {

    public Os os;

    public List<OsDefeito> buscaOSDefeito() {
        String json = wsGet("recebeOSDefeito/"+os.getCodigo().toString()+"/"+os.getBanco().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<OsDefeito>>() {}.getType();
        List<OsDefeito> retorno = gson.fromJson(json, collectionType);

        return retorno;
    }


}

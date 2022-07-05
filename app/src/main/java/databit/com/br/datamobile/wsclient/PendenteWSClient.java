package databit.com.br.datamobile.wsclient;

import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Pendente;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 12/05/2016.
 */
public class PendenteWSClient extends AndroidWsClient {

    public Os os;

    public List<Pendente> buscaPendente() {

        String json = wsGet("recebePendente/"+os.getNumserie().toString()+"/"+os.getBanco().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Pendente>>() {}.getType();
        List<Pendente> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }



}

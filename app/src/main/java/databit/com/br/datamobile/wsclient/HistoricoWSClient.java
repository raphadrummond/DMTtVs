package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.domain.Historico;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 29/04/2016.
 */
public class HistoricoWSClient extends AndroidWsClient {

    public Os os;

    public List<Historico> buscaHistorico() {

        String json = wsGet("recebeHistorico/"+os.getNumserie().toString()+"/"+os.getBanco().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Historico>>() {}.getType();
        List<Historico> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }

}

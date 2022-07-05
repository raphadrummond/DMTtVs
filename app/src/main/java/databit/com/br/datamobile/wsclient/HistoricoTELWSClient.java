package databit.com.br.datamobile.wsclient;

import java.util.List;

import databit.com.br.datamobile.domain.HistoricoTEL;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;


/**
 * Created by Sidney on 04/04/2018.
 */
public class HistoricoTELWSClient extends AndroidWsClient {

    public Os os;
    public List<HistoricoTEL> buscaHistoricoTEL() {

        String json = wsGet("recebeHistoricoTEL/"+os.getCodcli().toString()+"/"+os.getBanco().toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<HistoricoTEL>>() {}.getType();
        List<HistoricoTEL> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }


}

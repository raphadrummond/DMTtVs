package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.HistoricoTI;
import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 28/03/2018.
 */
public class HistoricoTIWSClient extends AndroidWsClient {

    public Os os;

    public List<HistoricoTI> buscaHistoricoTI() {
        String json = wsGet("recebeHistoricoTI/"+os.getCodcli().toString()+"/"+os.getBanco().toString()+"/"+
                                                 os.getVerlaboratorio().toString()+"/"+os.getVerremoto().toString()+"/"+
                                                 os.getVerpresencial().toString()+"/"+os.getVerremoto().toString());
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<HistoricoTI>>() {}.getType();
        List<HistoricoTI> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }

    public List<HistoricoTI> buscaHistoricoIMP() {
        String json = wsGet("recebeHistoricoIMP/"+os.getCodcli().toString()+"/"+os.getBanco().toString()+"/"+
                                                  os.getVersistema().toString()+"/"+os.getVerimplantacao().toString());
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<HistoricoTI>>() {}.getType();
        List<HistoricoTI> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }





}

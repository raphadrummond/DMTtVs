package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Ponto;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 24/10/2018.
 */
public class PontoWSClient extends AndroidWsClient {

    public String enviaPonto(Ponto ponto) {
        String jsonInformacao = gson.toJson(ponto);
        String sRetorno = wsPost("enviaPonto", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }

    public List<Ponto> buscaPonto() {
        String json = wsGet("recebePonto");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Ponto>>() {}.getType();
        List<Ponto> retorno = gson.fromJson(json, collectionType);
        return retorno;
    }


}

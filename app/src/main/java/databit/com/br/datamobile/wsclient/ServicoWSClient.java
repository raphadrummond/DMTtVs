package databit.com.br.datamobile.wsclient;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Servico;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 13/12/2017.
 */
public class ServicoWSClient extends AndroidWsClient {
    public List<Servico> buscaServico() {

        String json = wsGet("recebeServico");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Servico>>() {}.getType();
        List<Servico> retorno = gson.fromJson(json, collectionType);
        return retorno;

    }
}

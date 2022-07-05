package databit.com.br.datamobile.wsclient;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import databit.com.br.datamobile.domain.Composicao;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 13/12/2017.
 */
public class ComposicaoWSClient extends AndroidWsClient {
    public List<Composicao> buscaComposicao() {

        String json = wsGet("recebeComposicao");
        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");
        Type collectionType= new TypeToken<List<Composicao>>() {}.getType();
        List<Composicao> retorno = gson.fromJson(json, collectionType);
        return retorno;

    }

}

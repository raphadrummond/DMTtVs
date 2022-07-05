package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import databit.com.br.datamobile.domain.Os;
import databit.com.br.datamobile.domain.Serial;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 04/04/2018.
 */
public class SerialWSClient extends AndroidWsClient {

    public Os os;

    public List<Serial> buscaSerial() {

        Integer iTipo = os.getOperacaomobile() - 1;

        String json = wsGet("recebeSeriais/"+os.getCodigo().toString()+"/"+os.getBanco().toString()+"/"+iTipo.toString());

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Serial>>() {}.getType();
        List<Serial> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }

}

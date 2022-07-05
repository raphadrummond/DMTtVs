package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 16/06/2016.
 */
public class AparelhoWSClient extends AndroidWsClient {

    public String enviaLocalidade(Aparelho aparelho) {
        String jsonInformacao = gson.toJson(aparelho);
        String sRetorno = wsPost("enviaLocalidade", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }


}

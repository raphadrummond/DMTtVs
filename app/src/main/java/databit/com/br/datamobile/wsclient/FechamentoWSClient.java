package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Fechamento;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 16/06/2016.
 */
public class FechamentoWSClient extends AndroidWsClient {

    public String enviaFechamento(Fechamento fechamento) {
        String jsonInformacao = gson.toJson(fechamento);
        String sRetorno = wsPost("enviaFechamento", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }


}

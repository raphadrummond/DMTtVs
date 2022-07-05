package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 16/06/2016.
 */
public class ConfiguracaoWSClient extends AndroidWsClient {

    public String recebeConfiguracao(Configuracao configuracao) {
        String jsonConfiguracao = gson.toJson(configuracao);
        String sRetorno = wsPost("Configuracao", "{\"result\":[[" + jsonConfiguracao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }


}

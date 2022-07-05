package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Arquivo;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 17/04/2018.
 */
public class ArquivoWSClient extends AndroidWsClient {

    public String incluirArquivo(Arquivo arquivo) {
        String jsonInformacao = gson.toJson(arquivo);
        String sRetorno = wsPost("IncluirArquivo", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }

    public String excluirArquivo(Arquivo arquivo) {
        String jsonInformacao = gson.toJson(arquivo);
        String sRetorno = wsPost("ExcluirArquivo", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }



}

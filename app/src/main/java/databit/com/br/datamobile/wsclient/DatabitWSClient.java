package databit.com.br.datamobile.wsclient;

import databit.com.br.datamobile.domain.Aparelho;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.wsbase.AndroidWsClientDatabit;

public class DatabitWSClient extends AndroidWsClientDatabit {
    public String Autorizacao(Configuracao configuracao) {
        configuracao.setIp("databit");
        configuracao.setWebservice("databit");
        String jsonInformacao = gson.toJson(configuracao);
        String sRetorno = wsPost("Autorizacao", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }

    public String enviaAparelho(Aparelho aparelho) {
        String jsonInformacao = gson.toJson(aparelho);
        String sRetorno = wsPost("enviaAparelho", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }


}

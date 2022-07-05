package databit.com.br.datamobile.wsbase;

import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;

/**
 * Created by user on 11/04/2016.
 */
public class AndroidWsClient extends WsClient {

    @Override
    protected String getUrl() {
        Configuracao configuracao = new Configuracao();
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");
        return configuracao.getWebservice().toString();
    }

    @Override
    protected String getName() {
        return getUrl();
    }


}
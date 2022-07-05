package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Condicao;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 29/04/2016.
 */
public class CondicaoWSClient extends AndroidWsClient {

    public List<Condicao> buscaCondicao() {

        Configuracao configuracao = new Configuracao();
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        //String json = wsGet("recebeCondicao/"+sdf.format(configuracao.getDatasync()));
        String json = wsGet("recebeCondicao/2010-01-01");

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Condicao>>() {}.getType();
        List<Condicao> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }


}

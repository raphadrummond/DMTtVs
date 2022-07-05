package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.ServicoIncompleto;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 16/05/2016.
 */
public class ServicoIncompletoWSClient extends AndroidWsClient {
    public List<ServicoIncompleto> buscaServicoIncompleto() {

        Configuracao configuracao = new Configuracao();
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        //String json = wsGet("recebeServIncompleto/"+sdf.format(configuracao.getDatasync()));
        String json = wsGet("recebeServIncompleto/2010-01-01");

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<ServicoIncompleto>>() {}.getType();
        List<ServicoIncompleto> retorno = gson.fromJson(json, collectionType);

        return retorno;

    }

}

package databit.com.br.datamobile.wsclient;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import databit.com.br.datamobile.dao.ConfiguracaoDAO;
import databit.com.br.datamobile.domain.Configuracao;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.wsbase.AndroidWsClient;

/**
 * Created by Sidney on 22/04/2016.
 */
public class UsuarioWSClient extends AndroidWsClient {

    public List<Usuario> buscaUsuario() {

        Configuracao configuracao = new Configuracao();
        ConfiguracaoDAO configuracaoDAO = new ConfiguracaoDAO();
        configuracao = configuracaoDAO.procuraConfiguracao("id = 1");

        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");


        //String json = wsGet("recebeUsuario/"+configuracao.getCnpj().toString()+"/"+sdf.format(configuracao.getDatasync()));

        String json = wsGet("recebeUsuario/"+configuracao.getCnpj().toString()+"/2010-01-01");

        json = json.replace("{\"result\":[[","[");
        json = json.replace("]]}","]");

        Type collectionType= new TypeToken<List<Usuario>>() {}.getType();
        List<Usuario> retorno = gson.fromJson(json, collectionType);

        return retorno;
    }

    public String enviaSenha(Usuario usuario) {
        String jsonInformacao = gson.toJson(usuario);
        String sRetorno = wsPost("enviaSenha", "{\"result\":[[" + jsonInformacao + "]]}");
        sRetorno = sRetorno.replace("{\"result\":[\"","");
        sRetorno = sRetorno.replace("\"]}","");
        return sRetorno;
    }




}

package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Configuracao;

/**
 * Created by user on 05/04/2016.
 */
public class ConfiguracaoDAO extends BaseDAO<Configuracao> {

    // Listar pela Condição SQL
    public List<Configuracao> listarConfiguracao (String sFiltro) {
        List<Configuracao> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaConfiguracao(Configuracao configuracao) {
        return  super.createOrUpdate(configuracao);
    }

    // Excluir no Banco de Dados
    public boolean excluiConfiguracao(Configuracao configuracao) {
        return super.delete(configuracao);
    }

    // Procurar no Banco de Dados
    public Configuracao procuraConfiguracaoID(Configuracao configuracao) {
        return super.findByPK(configuracao);
    }

    // Listar todos os Registros
    public List<Configuracao> allConfiguracao () {
        List<Configuracao> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Configuracao procuraConfiguracao(String sFiltro) {
        Configuracao configuracao = super.findSQLUnique(sFiltro);
        return configuracao;
    }

}

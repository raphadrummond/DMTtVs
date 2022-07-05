package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Composicao;

/**
 * Created by Sidney on 13/12/2017.
 */
public class ComposicaoDAO extends BaseDAO<Composicao> {
    // Listar pela Condição SQL
    public List<Composicao> listarComposicao (String sFiltro) {
        List<Composicao> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaComposicao(Composicao composicao) {
        return  super.createOrUpdate(composicao);
    }

    // Excluir no Banco de Dados
    public boolean excluiComposicao(Composicao composicao) {
        return super.delete(composicao);
    }

    // Procurar no Banco de Dados
    public Composicao procuraComposicao(Composicao composicao) {
        return super.findByPK(composicao);
    }

    // Listar todos os Registros
    public List<Composicao> allComposicao () {
        List<Composicao> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Composição
    public Composicao procuraComposicao(String sFiltro) {
        Composicao composicao = super.findSQLUnique(sFiltro);
        return composicao;
    }

}

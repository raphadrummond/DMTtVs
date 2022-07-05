package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Informacao;

/**
 * Created by user on 05/04/2016.
 */
public class InformacaoDAO extends BaseDAO<Informacao> {

    // Listar pela Condição SQL
    public List<Informacao> listarInformacao (String sFiltro) {
        List<Informacao> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaInformacao(Informacao informacao) {
        return  super.createOrUpdate(informacao);
    }

    // Excluir no Banco de Dados
    public boolean excluiInformacao(Informacao informacao) {
        return super.delete(informacao);
    }

    // Procurar no Banco de Dados
    public Informacao procuraInformacao(Informacao informacao) {
        return super.findByPK(informacao);
    }

    // Listar todos os Registros
    public List<Informacao> allInformacao () {
        List<Informacao> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Informacao
    public Informacao procuraInformacao(String sFiltro) {
        Informacao informacao = super.findSQLUnique(sFiltro);
        return informacao;
    }


}

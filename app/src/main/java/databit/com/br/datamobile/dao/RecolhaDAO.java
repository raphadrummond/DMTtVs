package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Recolha;

/**
 * Created by Sidney on 16/04/2018.
 */
public class RecolhaDAO extends BaseDAO<Recolha> {
    // Listar pela Condição SQL
    public List<Recolha> listarRecolha (String sFiltro) {
        List<Recolha> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaRecolha(Recolha recolha) {
        return  super.createOrUpdate(recolha);
    }

    // Excluir no Banco de Dados
    public boolean excluiRecolha(Recolha recolha) {
        return super.delete(recolha);
    }

    // Procurar no Banco de Dados
    public Recolha procuraRecolha(Recolha recolha) {
        return super.findByPK(recolha);
    }

    // Listar todos os Registros
    public List<Recolha> allRecolha () {
        List<Recolha> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Recolha
    public Recolha procuraRecolha(String sFiltro) {
        Recolha recolha = super.findSQLUnique(sFiltro);
        return recolha;
    }



}

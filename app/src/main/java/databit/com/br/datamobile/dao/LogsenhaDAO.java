package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Logsenha;

/**
 * Created by user on 23/10/2018.
 */
public class LogsenhaDAO extends BaseDAO<Logsenha> {

    // Listar pela Condição SQL
    public List<Logsenha> listarLogsenha (String sFiltro) {
        List<Logsenha> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaLogsenha(Logsenha logsenha) {
        return  super.createOrUpdate(logsenha);
    }

    // Excluir no Banco de Dados
    public boolean excluiLogsenha(Logsenha logsenha) {
        return super.delete(logsenha);
    }

    // Procurar no Banco de Dados
    public Logsenha procuraLogsenhaID(Logsenha logsenha) {
        return super.findByPK(logsenha);
    }

    // Listar todos os Registros
    public List<Logsenha> allLogsenha() {
        List<Logsenha> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Logsenha procuraLogsenha(String sFiltro) {
        Logsenha logsenha = super.findSQLUnique(sFiltro);
        return logsenha;
    }




}

package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Defeito;

/**
 * Created by user on 05/04/2016.
 */
public class DefeitoDAO extends BaseDAO<Defeito> {

    // Listar pela Condição SQL
    public List<Defeito> listarDefeito (String sFiltro) {
        List<Defeito> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaDefeito(Defeito defeito) {
        return  super.createOrUpdate(defeito);
    }

    // Excluir no Banco de Dados
    public boolean excluiDefeito(Defeito defeito) {
        return super.delete(defeito);
    }

    // Procurar no Banco de Dados
    public Defeito procuraDefeitoID(Defeito defeito) {
        return super.findByPK(defeito);
    }

    // Listar todos os Registros
    public List<Defeito> allDefeito () {
        List<Defeito> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Defeito procuraDefeito(String sFiltro) {
        Defeito defeito = super.findSQLUnique(sFiltro);
        return defeito;
    }



}

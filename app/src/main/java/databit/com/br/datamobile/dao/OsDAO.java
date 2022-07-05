package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Os;

/**
 * Created by user on 05/04/2016.
 */
public class OsDAO extends BaseDAO<Os> {

    // Listar pela Condição SQL
    public List<Os> listarOs (String sFiltro) {
        List<Os> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaOs(Os os) {
        return  super.createOrUpdate(os);
    }

    // Excluir no Banco de Dados
    public boolean excluiOs(Os os) {
        return super.delete(os);
    }

    // Procurar no Banco de Dados
    public Os procuraOsID(Os os) {
        return super.findByPK(os);
    }

    // Listar todos os Registros
    public List<Os> allOs () {
        List<Os> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Os procuraOs(String sFiltro) {
        Os os = super.findSQLUnique(sFiltro);
        return os;
    }

}

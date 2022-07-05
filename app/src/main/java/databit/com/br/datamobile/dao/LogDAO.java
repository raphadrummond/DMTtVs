package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Log;

/**
 * Created by Sidney on 02/02/2018.
 */
public class LogDAO extends BaseDAO<Log> {

    // Listar pela Condição SQL
    public List<Log> listarLog (String sFiltro) {
        List<Log> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaLog(Log log) {
        return  super.createOrUpdate(log);
    }

    // Excluir no Banco de Dados
    public boolean excluiLog(Log log) {
        return super.delete(log);
    }

    // Procurar no Banco de Dados
    public Log procuraLogID(Log log) {
        return super.findByPK(log);
    }

    // Listar todos os Registros
    public List<Log> allLog() {
        List<Log> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Log procuraLog(String sFiltro) {
        Log log = super.findSQLUnique(sFiltro);
        return log;
    }

}

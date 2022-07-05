package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Logsinc;

/**
 * Created by Sidney on 02/02/2018.
 */
public class LogsincDAO extends BaseDAO<Logsinc> {

    // Listar pela Condição SQL
    public List<Logsinc> listarLog (String sFiltro) {
        List<Logsinc> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaLog(Logsinc logsinc) {
        return  super.createOrUpdate(logsinc);
    }

    // Excluir no Banco de Dados
    public boolean excluiLog(Logsinc logsinc) {
        return super.delete(logsinc);
    }

    // Procurar no Banco de Dados
    public Logsinc procuraLogID(Logsinc logsinc) {
        return super.findByPK(logsinc);
    }

    // Listar todos os Registros
    public List<Logsinc> allLog() {
        List<Logsinc> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Logsinc procuraLog(String sFiltro) {
        Logsinc logsinc = super.findSQLUnique(sFiltro);
        return logsinc;
    }

}

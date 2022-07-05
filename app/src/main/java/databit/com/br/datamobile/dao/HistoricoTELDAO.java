package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.HistoricoTEL;

/**
 * Created by Sidney on 04/04/2018.
 */
public class HistoricoTELDAO extends BaseDAO<HistoricoTEL> {

    // Listar pela Condição SQL
    public List<HistoricoTEL> listarHistoricoTEL (String sFiltro) {
        List<HistoricoTEL> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaHistoricoTEL(HistoricoTEL historicoTEL) {
        return  super.createOrUpdate(historicoTEL);
    }

    // Excluir no Banco de Dados
    public boolean excluiHistoricoTEL(HistoricoTEL historicoTEL) {
        return super.delete(historicoTEL);
    }

    // Procurar no Banco de Dados
    public HistoricoTEL procuraHistoricoTELID(HistoricoTEL historicoTEL) {
        return super.findByPK(historicoTEL);
    }

    // Listar todos os Registros
    public List<HistoricoTEL> allHistoricoTEL () {
        List<HistoricoTEL> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public HistoricoTEL procuraHistoricoTEL(String sFiltro) {
        HistoricoTEL historicoTEL = super.findSQLUnique(sFiltro);
        return historicoTEL;
    }


}

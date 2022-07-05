package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.HistoricoTI;

/**
 * Created by Sidney on 28/03/2018.
 */
public class HistoricoTIDAO extends BaseDAO<HistoricoTI> {
    public List<HistoricoTI> listarHistoricoTI (String sFiltro) {
        List<HistoricoTI> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaHistoricoTI(HistoricoTI historicoTI) {
        return  super.createOrUpdate(historicoTI);
    }

    // Excluir no Banco de Dados
    public boolean excluiHistoricoTI(HistoricoTI historicoTI) {
        return super.delete(historicoTI);
    }

    // Procurar no Banco de Dados
    public HistoricoTI procuraHistoricoTIID(HistoricoTI historicoTI) {
        return super.findByPK(historicoTI);
    }

    // Listar todos os Registros
    public List<HistoricoTI> allHistoricoTI () {
        List<HistoricoTI> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public HistoricoTI procuraHistoricoTI(String sFiltro) {
        HistoricoTI historicoTI = super.findSQLUnique(sFiltro);
        return historicoTI;
    }
}

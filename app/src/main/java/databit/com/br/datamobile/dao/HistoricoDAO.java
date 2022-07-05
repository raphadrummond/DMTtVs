package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Historico;

/**
 * Created by user on 05/04/2016.
 */
public class HistoricoDAO extends BaseDAO<Historico> {

    // Listar pela Condição SQL
    public List<Historico> listarHistorico (String sFiltro) {
        List<Historico> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaHistorico(Historico historico) {
        return  super.createOrUpdate(historico);
    }

    // Excluir no Banco de Dados
    public boolean excluiHistorico(Historico historico) {
        return super.delete(historico);
    }

    // Procurar no Banco de Dados
    public Historico procuraHistoricoID(Historico historico) {
        return super.findByPK(historico);
    }

    // Listar todos os Registros
    public List<Historico> allHistorico () {
        List<Historico> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Historico procuraHistorico(String sFiltro) {
        Historico historico = super.findSQLUnique(sFiltro);
        return historico;
    }

}

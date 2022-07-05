package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Fechamento;

/**
 * Created by user on 05/04/2016.
 */
public class FechamentoDAO extends BaseDAO<Fechamento> {

    // Listar pela Condição SQL
    public List<Fechamento> listarFechamento (String sFiltro) {
        List<Fechamento> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaFechamento(Fechamento fechamento) {
        return  super.createOrUpdate(fechamento);
    }

    // Excluir no Banco de Dados
    public boolean excluiFechamento(Fechamento fechamento) {
        return super.delete(fechamento);
    }

    // Procurar no Banco de Dados
    public Fechamento procuraFechamento(Fechamento fechamento) {
        return super.findByPK(fechamento);
    }

    // Listar todos os Registros
    public List<Fechamento> allFechamento () {
        List<Fechamento> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Fechamento procuraFechamento(String sFiltro) {
        Fechamento fechamento = super.findSQLUnique(sFiltro);
        return fechamento;
    }

}

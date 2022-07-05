package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Pendente;

/**
 * Created by user on 05/04/2016.
 */
public class PendenteDAO extends BaseDAO<Pendente> {

    // Listar pela Condição SQL
    public List<Pendente> listarPendente (String sFiltro) {
        List<Pendente> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaPendente(Pendente pendente) {
        return  super.createOrUpdate(pendente);
    }

    // Excluir no Banco de Dados
    public boolean excluiPendente(Pendente pendente) {
        return super.delete(pendente);
    }

    // Procurar no Banco de Dados
    public Pendente procuraPendenteID(Pendente pendente) {
        return super.findByPK(pendente);
    }

    // Listar todos os Registros
    public List<Pendente> allPendente () {
        List<Pendente> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Pendente
    public Pendente procuraPendente(String sFiltro) {
        Pendente pendente = super.findSQLUnique(sFiltro);
        return pendente;
    }

}

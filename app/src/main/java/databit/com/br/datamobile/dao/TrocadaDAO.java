package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Trocada;

/**
 * Created by user on 05/04/2016.
 */
public class TrocadaDAO extends BaseDAO<Trocada> {

    // Listar pela Condição SQL
    public List<Trocada> listarTrocada (String sFiltro) {
        List<Trocada> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaTrocada(Trocada trocada) {
        return  super.createOrUpdate(trocada);
    }

    // Excluir no Banco de Dados
    public boolean excluiTrocada(Trocada trocada) {
        return super.delete(trocada);
    }

    // Procurar no Banco de Dados
    public Trocada procuraTrocada(Trocada trocada) {
        return super.findByPK(trocada);
    }

    // Listar todos os Registros
    public List<Trocada> allTrocada () {
        List<Trocada> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar peça Trocada
    public Trocada procuraTrocada(String sFiltro) {
        Trocada trocada = super.findSQLUnique(sFiltro);
        return trocada;
    }

}

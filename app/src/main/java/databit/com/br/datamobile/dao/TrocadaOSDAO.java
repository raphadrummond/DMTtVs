package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.TrocadaOS;

/**
 * Created by Sidney on 13/12/2017.
 */
public class TrocadaOSDAO extends BaseDAO<TrocadaOS> {
    // Listar pela Condição SQL
    public List<TrocadaOS> listarTrocadaOS (String sFiltro) {
        List<TrocadaOS> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaTrocadaOS(TrocadaOS trocadaOS) {
        return  super.createOrUpdate(trocadaOS);
    }

    // Excluir no Banco de Dados
    public boolean excluiTrocadaOS(TrocadaOS trocadaOS) {
        return super.delete(trocadaOS);
    }

    // Procurar no Banco de Dados
    public TrocadaOS procuraTrocadaOS(TrocadaOS trocadaOS) {
        return super.findByPK(trocadaOS);
    }

    // Listar todos os Registros
    public List<TrocadaOS> allTrocadaOS () {
        List<TrocadaOS> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Peça Trocada
    public TrocadaOS procuraTrocadaOS(String sFiltro) {
        TrocadaOS trocadaOS = super.findSQLUnique(sFiltro);
        return trocadaOS;
    }


}

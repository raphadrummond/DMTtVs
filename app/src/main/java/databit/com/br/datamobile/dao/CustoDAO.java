package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Custo;

/**
 * Created by Sidney on 13/12/2017.
 */
public class CustoDAO extends BaseDAO<Custo> {
    // Listar pela Condição SQL
    public List<Custo> listarCusto (String sFiltro) {
        List<Custo> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaCusto(Custo custo) {
        return  super.createOrUpdate(custo);
    }

    // Excluir no Banco de Dados
    public boolean excluiCusto(Custo custo) {
        return super.delete(custo);
    }

    // Procurar no Banco de Dados
    public Custo procuraCusto(Custo custo) {
        return super.findByPK(custo);
    }

    // Listar todos os Registros
    public List<Custo> allCusto () {
        List<Custo> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Custo
    public Custo procuraCusto(String sFiltro) {
        Custo custo = super.findSQLUnique(sFiltro);
        return custo;
    }


}

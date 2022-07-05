package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Ponto;

/**
 * Created by user on 23/10/2018.
 */
public class PontoDAO extends BaseDAO<Ponto> {

    // Listar pela Condição SQL
    public List<Ponto> listarPonto (String sFiltro) {
        List<Ponto> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaPonto(Ponto ponto) {
        return  super.createOrUpdate(ponto);
    }

    // Excluir no Banco de Dados
    public boolean excluiPonto(Ponto ponto) {
        return super.delete(ponto);
    }

    // Procurar no Banco de Dados
    public Ponto procuraPontoID(Ponto ponto) {
        return super.findByPK(ponto);
    }

    // Listar todos os Registros
    public List<Ponto> allPonto() {
        List<Ponto> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Ponto procuraPonto(String sFiltro) {
        Ponto ponto = super.findSQLUnique(sFiltro);
        return ponto;
    }




}

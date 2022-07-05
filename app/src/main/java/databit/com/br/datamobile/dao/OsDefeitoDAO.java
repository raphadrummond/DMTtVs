package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.OsDefeito;

/**
 * Created by user on 05/04/2016.
 */
public class OsDefeitoDAO extends BaseDAO<OsDefeito> {

    // Listar pela Condição SQL
    public List<OsDefeito> listarOsDefeito (String sFiltro) {
        List<OsDefeito> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaOsDefeito(OsDefeito osdefeito) {
        return  super.createOrUpdate(osdefeito);
    }

    // Excluir no Banco de Dados
    public boolean excluiOsDefeito(OsDefeito osdefeito) {
        return super.delete(osdefeito);
    }

    // Procurar no Banco de Dados
    public OsDefeito procuraOsDefeito(OsDefeito osdefeito) {
        return super.findByPK(osdefeito);
    }

    // Listar todos os Registros
    public List<OsDefeito> allOsDefeito () {
        List<OsDefeito> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar OSDefeito
    public OsDefeito procuraOsDefeito(String sFiltro) {
        OsDefeito osdefeito = super.findSQLUnique(sFiltro);
        return osdefeito;
    }

}

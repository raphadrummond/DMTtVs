package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Configmobile;

/**
 * Created by Sidney on 28/03/2018.
 */
public class ConfigmobileDAO extends BaseDAO<Configmobile> {

    // Listar pela Condição SQL
    public List<Configmobile> listarConfigmobile (String sFiltro) {
        List<Configmobile> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaConfigmobile(Configmobile configmobile) {
        return  super.createOrUpdate(configmobile);
    }

    // Excluir no Banco de Dados
    public boolean excluiConfigmobile(Configmobile configmobile) {
        return super.delete(configmobile);
    }

    // Procurar no Banco de Dados
    public Configmobile procuraConfigmobileID(Configmobile configmobile) {
        return super.findByPK(configmobile);
    }

    // Listar todos os Registros
    public List<Configmobile> allConfigmobile () {
        List<Configmobile> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Configmobile procuraConfigmobile(String sFiltro) {
        Configmobile configmobile = super.findSQLUnique(sFiltro);
        return configmobile;
    }

}

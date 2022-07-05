package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.ConfigFTP;

/**
 * Created by Sidney on 28/03/2018.
 */
public class ConfigFTPDAO extends BaseDAO<ConfigFTP> {

    // Listar pela Condição SQL
    public List<ConfigFTP> listarFtp (String sFiltro) {
        List<ConfigFTP> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaFtp(ConfigFTP configFTP) {
        return  super.createOrUpdate(configFTP);
    }

    // Excluir no Banco de Dados
    public boolean excluiFtp(ConfigFTP configFTP) {
        return super.delete(configFTP);
    }

    // Procurar no Banco de Dados
    public ConfigFTP procuraFtpID(ConfigFTP configFTP) {
        return super.findByPK(configFTP);
    }

    // Listar todos os Registros
    public List<ConfigFTP> allFtp () {
        List<ConfigFTP> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public ConfigFTP procuraFtp(String sFiltro) {
        ConfigFTP configFTP = super.findSQLUnique(sFiltro);
        return configFTP;
    }


}

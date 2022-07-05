package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Serial;

/**
 * Created by Sidney on 04/04/2018.
 */
public class SerialDAO extends BaseDAO<Serial> {

    // Listar pela Condição SQL
    public List<Serial> listarSerial (String sFiltro) {
        List<Serial> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaSerial(Serial serial) {
        return  super.createOrUpdate(serial);
    }

    // Excluir no Banco de Dados
    public boolean excluiSerial(Serial serial) {
        return super.delete(serial);
    }

    // Procurar no Banco de Dados
    public Serial procuraSerialID(Serial serial) {
        return super.findByPK(serial);
    }

    // Listar todos os Registros
    public List<Serial> allSerial () {
        List<Serial> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Serial procuraSerial(String sFiltro) {
        Serial serial = super.findSQLUnique(sFiltro);
        return serial;
    }

}

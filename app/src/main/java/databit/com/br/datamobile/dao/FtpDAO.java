package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Ftp;

/**
 * Created by Sidney on 28/03/2018.
 */
public class FtpDAO extends BaseDAO<Ftp> {

    // Listar pela Condição SQL
    public List<Ftp> listarFtp (String sFiltro) {
        List<Ftp> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaFtp(Ftp ftp) {
        return  super.createOrUpdate(ftp);
    }

    // Excluir no Banco de Dados
    public boolean excluiFtp(Ftp ftp) {
        return super.delete(ftp);
    }

    // Procurar no Banco de Dados
    public Ftp procuraFtpID(Ftp ftp) {
        return super.findByPK(ftp);
    }

    // Listar todos os Registros
    public List<Ftp> allFtp () {
        List<Ftp> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Ftp procuraFtp(String sFiltro) {
        Ftp ftp = super.findSQLUnique(sFiltro);
        return ftp;
    }


}

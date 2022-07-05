package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Arquivo;

/**
 * Created by Sidney on 17/04/2018.
 */
public class ArquivoDAO extends BaseDAO<Arquivo> {

    // Listar pela Condição SQL
    public List<Arquivo> listarArquivo (String sFiltro) {
        List<Arquivo> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaArquivo(Arquivo arquivo) {
        return  super.createOrUpdate(arquivo);
    }

    // Excluir no Banco de Dados
    public boolean excluiArquivo(Arquivo arquivo) {
        return super.delete(arquivo);
    }

    // Procurar no Banco de Dados
    public Arquivo procuraArquivoID(Arquivo arquivo) {
        return super.findByPK(arquivo);
    }

    // Listar todos os Registros
    public List<Arquivo> allArquivo() {
        List<Arquivo> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Arquivo procuraArquivo(String sFiltro) {
        Arquivo arquivo = super.findSQLUnique(sFiltro);
        return arquivo;
    }

}

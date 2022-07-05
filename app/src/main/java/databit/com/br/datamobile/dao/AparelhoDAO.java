package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Aparelho;

/**
 * Created by user on 05/04/2016.
 */
public class AparelhoDAO extends BaseDAO<Aparelho> {

    // Listar pela Condição SQL
    public List<Aparelho> listarAparelho (String sFiltro) {
        List<Aparelho> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaAparelho(Aparelho aparelho) {
        return  super.createOrUpdate(aparelho);
    }

    // Excluir no Banco de Dados
    public boolean excluiAparelho(Aparelho aparelho) {
        return super.delete(aparelho);
    }

    // Procurar no Banco de Dados
    public Aparelho procuraAparelhoID(Aparelho aparelho) {
        return super.findByPK(aparelho);
    }

    // Listar todos os Registros
    public List<Aparelho> allAparelho() {
        List<Aparelho> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Aparelho procuraAparelho(String sFiltro) {
        Aparelho aparelho = super.findSQLUnique(sFiltro);
        return aparelho;
    }




}

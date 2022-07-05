package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Condicao;

/**
 * Created by user on 05/04/2016.
 */
public class CondicaoDAO extends BaseDAO<Condicao> {

        // Listar pela Condição SQL
    public List<Condicao> listarCondicao (String sFiltro) {
        List<Condicao> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }



    // Gravar no Banco de Dados
    public boolean gravaCondicao(Condicao condicao) {
        return  super.createOrUpdate(condicao);
    }

    // Excluir no Banco de Dados
    public boolean excluiCondicao(Condicao condicao) {
        return super.delete(condicao);
    }

    // Procurar no Banco de Dados
    public Condicao procuraCondicaoID(Condicao condicao) {
        return super.findByPK(condicao);
    }

    // Listar todos os Registros
    public List<Condicao> allCondicao() {
        List<Condicao> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }


    // Listar todos os Registros (Nome)
    public List<String> allNomeCondicao() {
        List<String> listanome = new ArrayList<String>();
        List<Condicao> lista = super.findAll();
        for (Condicao condicao : lista) {
            listanome.add(condicao.getNome().toString());
        }
        return listanome;
    }

    // Procurar Objeto
    public Condicao procuraCondicao(String sFiltro) {
        Condicao condicao = super.findSQLUnique(sFiltro);
        return condicao;
    }






}

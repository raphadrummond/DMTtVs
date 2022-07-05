package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Produto;

/**
 * Created by user on 05/04/2016.
 */
public class ProdutoDAO extends BaseDAO<Produto> {

    // Listar pela Condição SQL
    public List<Produto> listarProduto (String sFiltro) {
        List<Produto> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaProduto(Produto produto) {
        return  super.createOrUpdate(produto);
    }

    // Excluir no Banco de Dados
    public boolean excluiProduto(Produto produto) {
        return super.delete(produto);
    }

    // Procurar no Banco de Dados
    public Produto procuraProduto(Produto produto) {
        return super.findByPK(produto);
    }

    // Listar todos os Registros
    public List<Produto> allProduto () {
        List<Produto> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Produto
    public Produto procuraProduto(String sFiltro) {
        Produto produto = super.findSQLUnique(sFiltro);
        return produto;
    }


}

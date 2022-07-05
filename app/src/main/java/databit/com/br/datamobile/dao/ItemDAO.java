package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Item;

/**
 * Created by Sidney on 04/04/2018.
 */
public class ItemDAO extends BaseDAO<Item> {

    // Listar pela Condição SQL
    public List<Item> listarItem (String sFiltro) {
        List<Item> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaItem(Item item) {
        return  super.createOrUpdate(item);
    }

    // Excluir no Banco de Dados
    public boolean excluiItem(Item item) {
        return super.delete(item);
    }

    // Procurar no Banco de Dados
    public Item procuraItemID(Item item) {
        return super.findByPK(item);
    }

    // Listar todos os Registros
    public List<Item> allItem () {
        List<Item> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Objeto
    public Item procuraItem(String sFiltro) {
        Item item = super.findSQLUnique(sFiltro);
        return item;
    }

}

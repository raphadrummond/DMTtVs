package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Servico;

/**
 * Created by Sidney on 13/12/2017.
 */
public class ServicoDAO extends BaseDAO<Servico> {
    // Listar pela Condição SQL
    public List<Servico> listarServico (String sFiltro) {
        List<Servico> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaServico(Servico servico) {
        return  super.createOrUpdate(servico);
    }

    // Excluir no Banco de Dados
    public boolean excluiServico(Servico servico) {
        return super.delete(servico);
    }

    // Procurar no Banco de Dados
    public Servico procuraServico(Servico servico) {
        return super.findByPK(servico);
    }

    // Listar todos os Registros
    public List<Servico> allServico () {
        List<Servico> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Serviço
    public Servico procuraServico(String sFiltro) {
        Servico servico = super.findSQLUnique(sFiltro);
        return servico;
    }

}

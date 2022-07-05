package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.ServicoIncompleto;

/**
 * Created by user on 05/04/2016.
 */
public class ServicoIncompletoDAO extends BaseDAO<ServicoIncompleto> {

    // Listar pela Condição SQL
    public List<ServicoIncompleto> listarServicoIncompleto (String sFiltro) {
        List<ServicoIncompleto> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaServicoIncompleto(ServicoIncompleto servicoincompleto) {
        return  super.createOrUpdate(servicoincompleto);
    }

    // Excluir no Banco de Dados
    public boolean excluiServicoIncompleto(ServicoIncompleto servicoincompleto) {
        return super.delete(servicoincompleto);
    }

    // Procurar no Banco de Dados
    public ServicoIncompleto procuraServicoIncompletoID(ServicoIncompleto servicoincompleto) {
        return super.findByPK(servicoincompleto);
    }

    // Listar todos os Registros
    public List<ServicoIncompleto> allServicoIncompleto () {
        List<ServicoIncompleto> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }


    // Listar todos os Registros (Nome)
    public List<String> allNomeServicoIncompleto() {
        List<String> listanome = new ArrayList<String>();
        List<ServicoIncompleto> lista = super.findAll();
        for (ServicoIncompleto servicoIncompleto  : lista) {
            listanome.add(servicoIncompleto.getNome().toString());
        }
        return listanome;
    }


    // Procurar Serviço Incompleto
    public ServicoIncompleto procuraServicoIncompleto(String sFiltro) {
        ServicoIncompleto servicoincompleto = super.findSQLUnique(sFiltro);
        return servicoincompleto;
    }

}

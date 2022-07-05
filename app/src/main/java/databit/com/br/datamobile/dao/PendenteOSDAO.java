package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.PendenteOS;

/**
 * Created by Sidney on 13/12/2017.
 */
public class PendenteOSDAO extends BaseDAO<PendenteOS> {
    // Listar pela Condição SQL
    public List<PendenteOS> listarPendenteOS (String sFiltro) {
        List<PendenteOS> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaPendenteOS(PendenteOS pendenteOS) {
        return  super.createOrUpdate(pendenteOS);
    }

    // Excluir no Banco de Dados
    public boolean excluiPendenteOS(PendenteOS pendenteOS) {
        return super.delete(pendenteOS);
    }

    // Procurar no Banco de Dados
    public PendenteOS procuraPendenteOS(PendenteOS pendenteOS) {
        return super.findByPK(pendenteOS);
    }

    // Listar todos os Registros
    public List<PendenteOS> allPendenteOS () {
        List<PendenteOS> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar Peça Pendente
    public PendenteOS procuraPendenteOS(String sFiltro) {
        PendenteOS pendenteOS = super.findSQLUnique(sFiltro);
        return pendenteOS;
    }


}

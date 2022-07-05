package databit.com.br.datamobile.dao;

import java.util.ArrayList;
import java.util.List;

import databit.com.br.datamobile.database.BaseDAO;
import databit.com.br.datamobile.domain.Usuario;

/**
 * Created by user on 05/04/2016.
 */
public class UsuarioDAO extends BaseDAO<Usuario> {

    // Listar pela Condição SQL
    public List<Usuario> listarUsuario (String sFiltro) {
        List<Usuario> lista = new ArrayList<>();
        lista = super.findSQL(sFiltro);
        return lista;
    }

    // Gravar no Banco de Dados
    public boolean gravaUsuario(Usuario usuario) {
        return  super.createOrUpdate(usuario);
    }

    // Excluir no Banco de Dados
    public boolean excluiUsuario(Usuario usuario) {
        return super.delete(usuario);
    }

    // Procurar no Banco de Dados
    public Usuario procuraUsuario(Usuario usuario) {
        return super.findByPK(usuario);
    }

    // Listar todos os Registros
    public List<Usuario> allUsuario () {
        List<Usuario> lista = new ArrayList<>();
        lista = super.findAll();
        return lista;
    }

    // Procurar peça Usuario
    public Usuario procuraUsuario(String sFiltro) {
        Usuario usuario = super.findSQLUnique(sFiltro);
        return usuario;
    }

}

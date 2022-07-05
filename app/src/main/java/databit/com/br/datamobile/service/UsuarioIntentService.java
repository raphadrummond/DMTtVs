package databit.com.br.datamobile.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import databit.com.br.datamobile.dao.UsuarioDAO;
import databit.com.br.datamobile.domain.Usuario;
import databit.com.br.datamobile.wsclient.UsuarioWSClient;

public class UsuarioIntentService extends IntentService {

    public UsuarioIntentService() {
        super("UsuarioIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            UsuarioWSClient wsClient = new UsuarioWSClient();
            List<Usuario> listUsuario = wsClient.buscaUsuario();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            for (Usuario usuario : listUsuario) {
                usuarioDAO.createOrUpdate(usuario);
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

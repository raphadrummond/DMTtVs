package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

public class Logsenha {

    @DatabaseField(id = true)
    private String usuario;

    public Logsenha() {
    }

    public Logsenha(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}




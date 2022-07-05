package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 28/03/2018.
 */
public class Ftp implements Serializable {
    @DatabaseField(id = true)
    private Integer id;
    @DatabaseField
    private String endereco;
    @DatabaseField
    private String usuario;
    @DatabaseField
    private String senha;

    public Ftp() {
    }

    public Ftp(Integer id, String endereco, String usuario, String senha) {
        this.id = id;
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 13/12/2017.
 */
public class Servico implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String nome;

    public Servico() {
    }

    public Servico(String id, String banco, String codigo, String nome) {
        this.id = id;
        this.banco = banco;
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

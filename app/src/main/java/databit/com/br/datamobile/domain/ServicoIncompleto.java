package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 15/03/2016.
 */
public class ServicoIncompleto implements Serializable {

    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String situacao;

    public ServicoIncompleto(String codigo, String nome, String situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.situacao = situacao;
    }

    public ServicoIncompleto() {

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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}

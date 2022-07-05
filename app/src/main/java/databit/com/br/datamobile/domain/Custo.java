package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 12/12/2017.
 */
public class Custo implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String os;
    @DatabaseField
    private Integer item;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String nome;
    @DatabaseField
    private Float valor;

    public Custo() {
    }

    public Custo(String id, String banco, String os, Integer item, String codigo, String nome, Float valor) {
        this.id = id;
        this.banco = banco;
        this.os = os;
        this.item = item;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}

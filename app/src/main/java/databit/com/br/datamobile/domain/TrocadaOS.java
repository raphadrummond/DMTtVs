package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 12/12/2017.
 */
public class TrocadaOS implements Serializable {
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
    private String referencia;
    @DatabaseField
    private String nome;
    @DatabaseField
    private Integer qtprod;

    public TrocadaOS() {
    }

    public TrocadaOS(String id, String banco, String os, Integer item, String codigo, String referencia, String nome, Integer qtprod) {
        this.id = id;
        this.banco = banco;
        this.os = os;
        this.item = item;
        this.codigo = codigo;
        this.referencia = referencia;
        this.nome = nome;
        this.qtprod = qtprod;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtprod() {
        return qtprod;
    }

    public void setQtprod(Integer qtprod) {
        this.qtprod = qtprod;
    }
}

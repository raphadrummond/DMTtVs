package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 04/04/2018.
 */
public class Item implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String produto;
    @DatabaseField
    private String referencia;
    @DatabaseField
    private String nome;
    @DatabaseField
    private Float qtprod;
    @DatabaseField
    private String tabela;

    public Item() {
    }

    public Item(String id, String codigo, String produto, String referencia, String nome, Float qtprod, String tabela) {
        this.id = id;
        this.codigo = codigo;
        this.produto = produto;
        this.referencia = referencia;
        this.nome = nome;
        this.qtprod = qtprod;
        this.tabela = tabela;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

    public Float getQtprod() {
        return qtprod;
    }

    public void setQtprod(Float qtprod) {
        this.qtprod = qtprod;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }
}

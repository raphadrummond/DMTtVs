package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 04/04/2018.
 */
public class Serial implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String numserie;
    @DatabaseField
    private String produto;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String referencia;
    @DatabaseField
    private String tabela;
    @DatabaseField
    private String pat;

    public Serial() {
    }

    public Serial(String id, String codigo, String numserie, String produto, String nome, String referencia, String tabela, String pat) {
        this.id = id;
        this.codigo = codigo;
        this.numserie = numserie;
        this.produto = produto;
        this.nome = nome;
        this.referencia = referencia;
        this.tabela = tabela;
        this.pat = pat;
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

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }
}

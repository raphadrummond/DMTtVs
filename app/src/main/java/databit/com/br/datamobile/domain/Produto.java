package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 14/03/2016.
 */
public class Produto implements Serializable {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String referencia;
    @DatabaseField
    private String codbarras;
    @DatabaseField
    private String codauxiliar;
    @DatabaseField
    private String nome;

    public Produto() {
    }

    public Produto(String id, String banco, String codigo, String referencia, String codbarras, String codauxiliar, String nome) {
        this.id = id;
        this.banco = banco;
        this.codigo = codigo;
        this.referencia = referencia;
        this.codbarras = codbarras;
        this.codauxiliar = codauxiliar;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public String getCodauxiliar() {
        return codauxiliar;
    }

    public void setCodauxiliar(String codauxiliar) {
        this.codauxiliar = codauxiliar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

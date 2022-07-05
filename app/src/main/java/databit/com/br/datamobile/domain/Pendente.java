package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 15/03/2016.
 */
public class Pendente implements Serializable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private Date cadastro;
    @DatabaseField
    private String operador;
    @DatabaseField
    private String produto;
    @DatabaseField
    private Integer qtprod;
    @DatabaseField
    private String codauxiliar;
    @DatabaseField
    private String codbarras;
    @DatabaseField
    private String nomeprod;
    @DatabaseField
    private String referencia;
    @DatabaseField
    private String sync;
    @DatabaseField
    private String numserie;

    public Pendente() {

    }

    public Pendente(String id, String codigo, Date cadastro, String operador, String produto, Integer qtprod, String codauxiliar, String codbarras, String nomeprod, String referencia, String sync, String numserie) {
        this.id = id;
        this.codigo = codigo;
        this.cadastro = cadastro;
        this.operador = operador;
        this.produto = produto;
        this.qtprod = qtprod;
        this.codauxiliar = codauxiliar;
        this.codbarras = codbarras;
        this.nomeprod = nomeprod;
        this.referencia = referencia;
        this.sync = sync;
        this.numserie = numserie;
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

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQtprod() {
        return qtprod;
    }

    public void setQtprod(Integer qtprod) {
        this.qtprod = qtprod;
    }

    public String getCodauxiliar() {
        return codauxiliar;
    }

    public void setCodauxiliar(String codauxiliar) {
        this.codauxiliar = codauxiliar;
    }

    public String getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public String getNomeprod() {
        return nomeprod;
    }

    public void setNomeprod(String nomeprod) {
        this.nomeprod = nomeprod;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }
}

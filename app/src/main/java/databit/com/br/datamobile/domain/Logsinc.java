package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 02/02/2018.
 */
public class Logsinc implements Serializable {
    @DatabaseField
    private String os;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String evento;
    @DatabaseField
    private String retorno;
    @DatabaseField(id = true)
    private Date data;
    @DatabaseField
    private Integer erro;
    @DatabaseField
    private Integer operacaomobile;

    public Logsinc() {
    }

    public Logsinc(String os, String banco, String evento, String retorno, Date data, Integer erro, Integer operacaomobile) {
        this.os = os;
        this.banco = banco;
        this.evento = evento;
        this.retorno = retorno;
        this.data = data;
        this.erro = erro;
        this.operacaomobile = operacaomobile;
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

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getErro() {
        return erro;
    }

    public void setErro(Integer erro) {
        this.erro = erro;
    }

    public Integer getOperacaomobile() {
        return operacaomobile;
    }

    public void setOperacaomobile(Integer operacaomobile) {
        this.operacaomobile = operacaomobile;
    }
}

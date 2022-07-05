package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 17/04/2018.
 */
public class Arquivo implements Serializable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private String banco;
    @DatabaseField
    private Integer operacaomobile;
    @DatabaseField
    private String arquivo;
    @DatabaseField
    private String diretorio;
    @DatabaseField
    private Integer item;

    public Arquivo() {
    }

    public Arquivo(String id, String codigo, String banco, Integer operacaomobile, String arquivo, String diretorio, Integer item) {
        this.id = id;
        this.codigo = codigo;
        this.banco = banco;
        this.operacaomobile = operacaomobile;
        this.arquivo = arquivo;
        this.diretorio = diretorio;
        this.item = item;
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

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getOperacaomobile() {
        return operacaomobile;
    }

    public void setOperacaomobile(Integer operacaomobile) {
        this.operacaomobile = operacaomobile;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }
}

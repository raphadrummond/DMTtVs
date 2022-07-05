package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by user on 05/04/2016.
 */
public class OsDefeito {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private Date cadastro;
    @DatabaseField
    private String operador;
    @DatabaseField
    private Date alteracao;
    @DatabaseField
    private String opaltera;
    @DatabaseField
    private String defeito;
    @DatabaseField
    private String obs;
    @DatabaseField
    private String nomedefeito;
    @DatabaseField
    private String sync;
    @DatabaseField
    private String solucao;
    @DatabaseField
    private String numserie;


    public OsDefeito (){

    }

    public OsDefeito(String id, String codigo, Date cadastro, String operador, Date alteracao, String opaltera, String defeito, String obs, String nomedefeito, String sync, String solucao, String numserie) {
        this.id = id;
        this.codigo = codigo;
        this.cadastro = cadastro;
        this.operador = operador;
        this.alteracao = alteracao;
        this.opaltera = opaltera;
        this.defeito = defeito;
        this.obs = obs;
        this.nomedefeito = nomedefeito;
        this.sync = sync;
        this.solucao = solucao;
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

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public String getOpaltera() {
        return opaltera;
    }

    public void setOpaltera(String opaltera) {
        this.opaltera = opaltera;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNomedefeito() {
        return nomedefeito;
    }

    public void setNomedefeito(String nomedefeito) {
        this.nomedefeito = nomedefeito;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }
}

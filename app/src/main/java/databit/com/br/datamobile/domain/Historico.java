package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 15/03/2016.
 */
public class Historico implements Serializable {

    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private Date abertura;
    @DatabaseField
    private Date fechamento;
    @DatabaseField
    private String atendente;
    @DatabaseField
    private String solicitante;
    @DatabaseField
    private String condicao;
    @DatabaseField
    private String tecnico;
    @DatabaseField
    private Integer contpb;
    @DatabaseField
    private Integer contcolor;
    @DatabaseField
    private Integer cilindropb;
    @DatabaseField
    private Integer cilindrocolor;
    @DatabaseField
    private Integer reveladorpb;
    @DatabaseField
    private Integer reveladorcolor;
    @DatabaseField
    private Integer fusor;
    @DatabaseField
    private Integer belt;
    @DatabaseField
    private Integer preventiva;
    @DatabaseField
    private Integer reservatorio;
    @DatabaseField
    private String obs;
    @DatabaseField
    private String numserie;

    public Historico() {

    }

    public Historico(String codigo, Date abertura, Date fechamento, String atendente, String solicitante, String condicao, String tecnico, Integer contpb, Integer contcolor, Integer cilindropb, Integer cilindrocolor, Integer reveladorpb, Integer reveladorcolor, Integer fusor, Integer belt, Integer preventiva, Integer reservatorio, String obs, String numserie) {
        this.codigo = codigo;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.atendente = atendente;
        this.solicitante = solicitante;
        this.condicao = condicao;
        this.tecnico = tecnico;
        this.contpb = contpb;
        this.contcolor = contcolor;
        this.cilindropb = cilindropb;
        this.cilindrocolor = cilindrocolor;
        this.reveladorpb = reveladorpb;
        this.reveladorcolor = reveladorcolor;
        this.fusor = fusor;
        this.belt = belt;
        this.preventiva = preventiva;
        this.reservatorio = reservatorio;
        this.obs = obs;
        this.numserie = numserie;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getContpb() {
        return contpb;
    }

    public void setContpb(Integer contpb) {
        this.contpb = contpb;
    }

    public Integer getContcolor() {
        return contcolor;
    }

    public void setContcolor(Integer contcolor) {
        this.contcolor = contcolor;
    }

    public Integer getCilindropb() {
        return cilindropb;
    }

    public void setCilindropb(Integer cilindropb) {
        this.cilindropb = cilindropb;
    }

    public Integer getCilindrocolor() {
        return cilindrocolor;
    }

    public void setCilindrocolor(Integer cilindrocolor) {
        this.cilindrocolor = cilindrocolor;
    }

    public Integer getReveladorpb() {
        return reveladorpb;
    }

    public void setReveladorpb(Integer reveladorpb) {
        this.reveladorpb = reveladorpb;
    }

    public Integer getReveladorcolor() {
        return reveladorcolor;
    }

    public void setReveladorcolor(Integer reveladorcolor) {
        this.reveladorcolor = reveladorcolor;
    }

    public Integer getFusor() {
        return fusor;
    }

    public void setFusor(Integer fusor) {
        this.fusor = fusor;
    }

    public Integer getBelt() {
        return belt;
    }

    public void setBelt(Integer belt) {
        this.belt = belt;
    }

    public Integer getPreventiva() {
        return preventiva;
    }

    public void setPreventiva(Integer preventiva) {
        this.preventiva = preventiva;
    }

    public Integer getReservatorio() {
        return reservatorio;
    }

    public void setReservatorio(Integer reservatorio) {
        this.reservatorio = reservatorio;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }
}

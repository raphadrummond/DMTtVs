package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 15/03/2016.
 */
public class Configuracao implements Serializable {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String cnpj;

    @DatabaseField
    private String coddatabit;

    @DatabaseField
    private String webservice;

    @DatabaseField
    private Date datasync;

    @DatabaseField
    private String ip;

    @DatabaseField
    private String maischeckin;

    @DatabaseField
    private String obrkm;

    @DatabaseField
    private String tipolan;

    @DatabaseField
    private Integer temposinc;

    @DatabaseField
    private Integer temposmens;

    @DatabaseField
    private String req;

    @DatabaseField
    private String recolha;

    @DatabaseField
    private String ponto;

    @DatabaseField
    private Integer intervalo;

    @DatabaseField
    private String horaintervalo;

    @DatabaseField
    private String horafinal;

    @DatabaseField
    private String horainimanha;

    @DatabaseField
    private String horafimmanha;

    @DatabaseField
    private String horainitarde;

    @DatabaseField
    private String horafimtarde;

    public Configuracao() {
    }

    public Configuracao(Integer id, String cnpj, String coddatabit, String webservice, Date datasync, String ip, String maischeckin, String obrkm, String tipolan, Integer temposinc, Integer temposmens, String req, String recolha, String ponto, Integer intervalo, String horaintervalo, String horafinal, String horainimanha, String horafimmanha, String horainitarde, String horafimtarde) {
        this.id = id;
        this.cnpj = cnpj;
        this.coddatabit = coddatabit;
        this.webservice = webservice;
        this.datasync = datasync;
        this.ip = ip;
        this.maischeckin = maischeckin;
        this.obrkm = obrkm;
        this.tipolan = tipolan;
        this.temposinc = temposinc;
        this.temposmens = temposmens;
        this.req = req;
        this.recolha = recolha;
        this.ponto = ponto;
        this.intervalo = intervalo;
        this.horaintervalo = horaintervalo;
        this.horafinal = horafinal;
        this.horainimanha = horainimanha;
        this.horafimmanha = horafimmanha;
        this.horainitarde = horainitarde;
        this.horafimtarde = horafimtarde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCoddatabit() {
        return coddatabit;
    }

    public void setCoddatabit(String coddatabit) {
        this.coddatabit = coddatabit;
    }

    public String getWebservice() {
        return webservice;
    }

    public void setWebservice(String webservice) {
        this.webservice = webservice;
    }

    public Date getDatasync() {
        return datasync;
    }

    public void setDatasync(Date datasync) {
        this.datasync = datasync;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMaischeckin() {
        return maischeckin;
    }

    public void setMaischeckin(String maischeckin) {
        this.maischeckin = maischeckin;
    }

    public String getObrkm() {
        return obrkm;
    }

    public void setObrkm(String obrkm) {
        this.obrkm = obrkm;
    }

    public String getTipolan() {
        return tipolan;
    }

    public void setTipolan(String tipolan) {
        this.tipolan = tipolan;
    }

    public Integer getTemposinc() {
        return temposinc;
    }

    public void setTemposinc(Integer temposinc) {
        this.temposinc = temposinc;
    }

    public Integer getTemposmens() {
        return temposmens;
    }

    public void setTemposmens(Integer temposmens) {
        this.temposmens = temposmens;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getRecolha() {
        return recolha;
    }

    public void setRecolha(String recolha) {
        this.recolha = recolha;
    }

    public String getPonto() {
        return ponto;
    }

    public void setPonto(String ponto) {
        this.ponto = ponto;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public String getHoraintervalo() {
        return horaintervalo;
    }

    public void setHoraintervalo(String horaintervalo) {
        this.horaintervalo = horaintervalo;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(String horafinal) {
        this.horafinal = horafinal;
    }

    public String getHorainimanha() {
        return horainimanha;
    }

    public void setHorainimanha(String horainimanha) {
        this.horainimanha = horainimanha;
    }

    public String getHorafimmanha() {
        return horafimmanha;
    }

    public void setHorafimmanha(String horafimmanha) {
        this.horafimmanha = horafimmanha;
    }

    public String getHorainitarde() {
        return horainitarde;
    }

    public void setHorainitarde(String horainitarde) {
        this.horainitarde = horainitarde;
    }

    public String getHorafimtarde() {
        return horafimtarde;
    }

    public void setHorafimtarde(String horafimtarde) {
        this.horafimtarde = horafimtarde;
    }
}

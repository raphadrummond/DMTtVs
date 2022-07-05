package databit.com.br.datamobile.domain;

import android.provider.ContactsContract;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 15/03/2016.
 */
public class Fechamento implements Serializable {

    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private Integer contador;
    @DatabaseField
    private Integer credcopias;
    @DatabaseField
    private String cilindro;
    @DatabaseField
    private String sitcilindro;
    @DatabaseField
    private Integer contcilindro;
    @DatabaseField
    private String revelador;
    @DatabaseField
    private String condicao;
    @DatabaseField
    private String incompleto;
    @DatabaseField
    private String servincompl;
    @DatabaseField
    private String obs;
    @DatabaseField
    private Integer contadorc;
    @DatabaseField
    private Integer credcopiasc;
    @DatabaseField
    private String fusor;
    @DatabaseField
    private String belt;
    @DatabaseField
    private Integer kminicial;
    @DatabaseField
    private Integer kmfinal;
    @DatabaseField
    private String cilindroc;
    @DatabaseField
    private String reveladorc;
    @DatabaseField
    private String sitcilindroc;
    @DatabaseField
    private Integer contcilindroc;
    @DatabaseField
    private String reservatorio;
    @DatabaseField
    private String placa;
    @DatabaseField
    private String sync;
    @DatabaseField
    private String pendente;
    @DatabaseField
    private String trocada;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] assinatura;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String bancoos;
    @DatabaseField
    private String nometec;
    @DatabaseField
    private Date data;

    public Fechamento() {
    }

    public Fechamento(String codigo, Integer contador, Integer credcopias, String cilindro, String sitcilindro, Integer contcilindro, String revelador, String condicao, String incompleto, String servincompl, String obs, Integer contadorc, Integer credcopiasc, String fusor, String belt, Integer kminicial, Integer kmfinal, String cilindroc, String reveladorc, String sitcilindroc, Integer contcilindroc, String reservatorio, String placa, String sync, String pendente, String trocada, byte[] assinatura, String banco, String bancoos, String nometec, Date data) {
        this.codigo = codigo;
        this.contador = contador;
        this.credcopias = credcopias;
        this.cilindro = cilindro;
        this.sitcilindro = sitcilindro;
        this.contcilindro = contcilindro;
        this.revelador = revelador;
        this.condicao = condicao;
        this.incompleto = incompleto;
        this.servincompl = servincompl;
        this.obs = obs;
        this.contadorc = contadorc;
        this.credcopiasc = credcopiasc;
        this.fusor = fusor;
        this.belt = belt;
        this.kminicial = kminicial;
        this.kmfinal = kmfinal;
        this.cilindroc = cilindroc;
        this.reveladorc = reveladorc;
        this.sitcilindroc = sitcilindroc;
        this.contcilindroc = contcilindroc;
        this.reservatorio = reservatorio;
        this.placa = placa;
        this.sync = sync;
        this.pendente = pendente;
        this.trocada = trocada;
        this.assinatura = assinatura;
        this.banco = banco;
        this.bancoos = bancoos;
        this.nometec = nometec;
        this.data = data;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Integer getCredcopias() {
        return credcopias;
    }

    public void setCredcopias(Integer credcopias) {
        this.credcopias = credcopias;
    }

    public String getCilindro() {
        return cilindro;
    }

    public void setCilindro(String cilindro) {
        this.cilindro = cilindro;
    }

    public String getSitcilindro() {
        return sitcilindro;
    }

    public void setSitcilindro(String sitcilindro) {
        this.sitcilindro = sitcilindro;
    }

    public Integer getContcilindro() {
        return contcilindro;
    }

    public void setContcilindro(Integer contcilindro) {
        this.contcilindro = contcilindro;
    }

    public String getRevelador() {
        return revelador;
    }

    public void setRevelador(String revelador) {
        this.revelador = revelador;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getIncompleto() {
        return incompleto;
    }

    public void setIncompleto(String incompleto) {
        this.incompleto = incompleto;
    }

    public String getServincompl() {
        return servincompl;
    }

    public void setServincompl(String servincompl) {
        this.servincompl = servincompl;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getContadorc() {
        return contadorc;
    }

    public void setContadorc(Integer contadorc) {
        this.contadorc = contadorc;
    }

    public Integer getCredcopiasc() {
        return credcopiasc;
    }

    public void setCredcopiasc(Integer credcopiasc) {
        this.credcopiasc = credcopiasc;
    }

    public String getFusor() {
        return fusor;
    }

    public void setFusor(String fusor) {
        this.fusor = fusor;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public Integer getKminicial() {
        return kminicial;
    }

    public void setKminicial(Integer kminicial) {
        this.kminicial = kminicial;
    }

    public Integer getKmfinal() {
        return kmfinal;
    }

    public void setKmfinal(Integer kmfinal) {
        this.kmfinal = kmfinal;
    }

    public String getCilindroc() {
        return cilindroc;
    }

    public void setCilindroc(String cilindroc) {
        this.cilindroc = cilindroc;
    }

    public String getReveladorc() {
        return reveladorc;
    }

    public void setReveladorc(String reveladorc) {
        this.reveladorc = reveladorc;
    }

    public String getSitcilindroc() {
        return sitcilindroc;
    }

    public void setSitcilindroc(String sitcilindroc) {
        this.sitcilindroc = sitcilindroc;
    }

    public Integer getContcilindroc() {
        return contcilindroc;
    }

    public void setContcilindroc(Integer contcilindroc) {
        this.contcilindroc = contcilindroc;
    }

    public String getReservatorio() {
        return reservatorio;
    }

    public void setReservatorio(String reservatorio) {
        this.reservatorio = reservatorio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getPendente() {
        return pendente;
    }

    public void setPendente(String pendente) {
        this.pendente = pendente;
    }

    public String getTrocada() {
        return trocada;
    }

    public void setTrocada(String trocada) {
        this.trocada = trocada;
    }

    public byte[] getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBancoos() {
        return bancoos;
    }

    public void setBancoos(String bancoos) {
        this.bancoos = bancoos;
    }

    public String getNometec() {
        return nometec;
    }

    public void setNometec(String nometec) {
        this.nometec = nometec;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

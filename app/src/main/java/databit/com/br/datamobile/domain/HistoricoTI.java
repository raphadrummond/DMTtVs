package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 28/03/2018.
 */
public class HistoricoTI implements Serializable {
    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private Date data;
    @DatabaseField
    private Date execucao;
    @DatabaseField
    private String codcli;
    @DatabaseField
    private String nomecli;
    @DatabaseField
    private String contato;
    @DatabaseField
    private String nometec;
    @DatabaseField
    private String horascom;
    @DatabaseField
    private String horasfim;
    @DatabaseField
    private String obs;
    @DatabaseField
    private String defeito;
    @DatabaseField
    private String laudo;
    @DatabaseField
    private String nomeoperacao;
    @DatabaseField
    private String projeto;
    @DatabaseField
    private Integer operacao;
    @DatabaseField
    private String horastrab;
    @DatabaseField
    private String nomemodulo;

    public HistoricoTI() {
    }

    public HistoricoTI(String codigo, Date data, Date execucao, String codcli, String nomecli, String contato, String nometec, String horascom, String horasfim, String obs, String defeito, String laudo, String nomeoperacao, String projeto, Integer operacao, String horastrab, String nomemodulo) {
        this.codigo = codigo;
        this.data = data;
        this.execucao = execucao;
        this.codcli = codcli;
        this.nomecli = nomecli;
        this.contato = contato;
        this.nometec = nometec;
        this.horascom = horascom;
        this.horasfim = horasfim;
        this.obs = obs;
        this.defeito = defeito;
        this.laudo = laudo;
        this.nomeoperacao = nomeoperacao;
        this.projeto = projeto;
        this.operacao = operacao;
        this.horastrab = horastrab;
        this.nomemodulo = nomemodulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getExecucao() {
        return execucao;
    }

    public void setExecucao(Date execucao) {
        this.execucao = execucao;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNometec() {
        return nometec;
    }

    public void setNometec(String nometec) {
        this.nometec = nometec;
    }

    public String getHorascom() {
        return horascom;
    }

    public void setHorascom(String horascom) {
        this.horascom = horascom;
    }

    public String getHorasfim() {
        return horasfim;
    }

    public void setHorasfim(String horasfim) {
        this.horasfim = horasfim;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getNomeoperacao() {
        return nomeoperacao;
    }

    public void setNomeoperacao(String nomeoperacao) {
        this.nomeoperacao = nomeoperacao;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public Integer getOperacao() {
        return operacao;
    }

    public void setOperacao(Integer operacao) {
        this.operacao = operacao;
    }

    public String getHorastrab() {
        return horastrab;
    }

    public void setHorastrab(String horastrab) {
        this.horastrab = horastrab;
    }

    public String getNomemodulo() {
        return nomemodulo;
    }

    public void setNomemodulo(String nomemodulo) {
        this.nomemodulo = nomemodulo;
    }
}

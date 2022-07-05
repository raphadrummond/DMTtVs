package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 04/04/2018.
 */
public class HistoricoTEL implements Serializable {
    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private Date data;
    @DatabaseField
    private String operador;
    @DatabaseField
    private String contato;
    @DatabaseField
    private Date previsao;
    @DatabaseField
    private String nomestatus;
    @DatabaseField
    private String operadoratual;
    @DatabaseField
    private String concluido;
    @DatabaseField
    private Integer qtde;
    @DatabaseField
    private Float vlrproposta;
    @DatabaseField
    private String atendimento;
    @DatabaseField
    private String resultado;
    @DatabaseField
    private String classificacao;
    @DatabaseField
    private String codcli;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String resposta;


    public HistoricoTEL() {
    }

    public HistoricoTEL(String codigo, Date data, String operador, String contato, Date previsao, String nomestatus, String operadoratual, String concluido, Integer qtde, Float vlrproposta, String atendimento, String resultado, String classificacao, String codcli, String banco, String resposta) {
        this.codigo = codigo;
        this.data = data;
        this.operador = operador;
        this.contato = contato;
        this.previsao = previsao;
        this.nomestatus = nomestatus;
        this.operadoratual = operadoratual;
        this.concluido = concluido;
        this.qtde = qtde;
        this.vlrproposta = vlrproposta;
        this.atendimento = atendimento;
        this.resultado = resultado;
        this.classificacao = classificacao;
        this.codcli = codcli;
        this.banco = banco;
        this.resposta = resposta;
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

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Date getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Date previsao) {
        this.previsao = previsao;
    }

    public String getNomestatus() {
        return nomestatus;
    }

    public void setNomestatus(String nomestatus) {
        this.nomestatus = nomestatus;
    }

    public String getOperadoratual() {
        return operadoratual;
    }

    public void setOperadoratual(String operadoratual) {
        this.operadoratual = operadoratual;
    }

    public String getConcluido() {
        return concluido;
    }

    public void setConcluido(String concluido) {
        this.concluido = concluido;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Float getVlrproposta() {
        return vlrproposta;
    }

    public void setVlrproposta(Float vlrproposta) {
        this.vlrproposta = vlrproposta;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}

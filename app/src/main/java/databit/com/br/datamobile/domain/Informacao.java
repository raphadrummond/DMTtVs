package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 17/05/2016.
 */
public class Informacao implements Serializable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String codigo;
    @DatabaseField
    private Integer tipo;
    @DatabaseField
    private Date data;
    @DatabaseField
    private String end;
    @DatabaseField
    private Integer num;
    @DatabaseField
    private String comp;
    @DatabaseField
    private String bairro;
    @DatabaseField
    private String cidade;
    @DatabaseField
    private String estado;
    @DatabaseField
    private String cep;
    @DatabaseField
    private Double latitude;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private String codcli;
    @DatabaseField
    private String codtec;
    @DatabaseField
    private String nometec;
    @DatabaseField
    private String sync;
    @DatabaseField
    private String codemp;
    @DatabaseField
    private String banco;
    @DatabaseField
    private String bancoos;
    public Informacao() {
    }

    public Informacao(String id, String codigo, Integer tipo, Date data, String end, Integer num, String comp, String bairro, String cidade, String estado, String cep, Double latitude, Double longitude, String codcli, String codtec, String nometec, String sync, String codemp, String banco, String bancoos) {
        this.id = id;
        this.codigo = codigo;
        this.tipo = tipo;
        this.data = data;
        this.end = end;
        this.num = num;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codcli = codcli;
        this.codtec = codtec;
        this.nometec = nometec;
        this.sync = sync;
        this.codemp = codemp;
        this.banco = banco;
        this.bancoos = bancoos;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getCodtec() {
        return codtec;
    }

    public void setCodtec(String codtec) {
        this.codtec = codtec;
    }

    public String getNometec() {
        return nometec;
    }

    public void setNometec(String nometec) {
        this.nometec = nometec;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getCodemp() {
        return codemp;
    }

    public void setCodemp(String codemp) {
        this.codemp = codemp;
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
}

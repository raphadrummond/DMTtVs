package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Ponto {

    @DatabaseField(id = true)
    private Integer id;
    @DatabaseField
    private Date data;
    @DatabaseField
    private Integer tipo;
    @DatabaseField
    private Date dataponto;
    @DatabaseField
    private Double latitude;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private String usuario;
    @DatabaseField
    private String sdata;
    @DatabaseField
    private String local;
    @DatabaseField
    private String fusohorario;
    @DatabaseField
    private String integra;
    @DatabaseField
    private String sync;

    public Ponto() {
    }

    public Ponto(Integer id, Date data, Integer tipo, Date dataponto, Double latitude, Double longitude, String usuario, String sdata, String local, String fusohorario, String integra, String sync) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.dataponto = dataponto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.usuario = usuario;
        this.sdata = sdata;
        this.local = local;
        this.fusohorario = fusohorario;
        this.integra = integra;
        this.sync = sync;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Date getDataponto() {
        return dataponto;
    }

    public void setDataponto(Date dataponto) {
        this.dataponto = dataponto;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSdata() {
        return sdata;
    }

    public void setSdata(String sdata) {
        this.sdata = sdata;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getFusohorario() {
        return fusohorario;
    }

    public void setFusohorario(String fusohorario) {
        this.fusohorario = fusohorario;
    }

    public String getIntegra() {
        return integra;
    }

    public void setIntegra(String integra) {
        this.integra = integra;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }
}



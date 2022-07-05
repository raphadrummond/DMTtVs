package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sidney on 26/04/2016.
 */
public class Aparelho implements Serializable {

    @DatabaseField(id = true)
    private Integer id;
    @DatabaseField
    private String usuario;
    @DatabaseField
    private String serial;
    @DatabaseField
    private String versao;
    @DatabaseField
    private String modelo;
    @DatabaseField
    private String fabricante;
    @DatabaseField
    private Integer versaocode;
    @DatabaseField
    private String versaoname;
    @DatabaseField
    private String token;
    @DatabaseField
    private String login;
    @DatabaseField
    private String coddatabit;
    @DatabaseField
    private Double latitude;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private Date data;

    public Aparelho() {
    }

    public Aparelho(Integer id, String usuario, String serial, String versao, String modelo, String fabricante, Integer versaocode, String versaoname, String token, String login, String coddatabit, Double latitude, Double longitude, Date data) {
        this.id = id;
        this.usuario = usuario;
        this.serial = serial;
        this.versao = versao;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.versaocode = versaocode;
        this.versaoname = versaoname;
        this.token = token;
        this.login = login;
        this.coddatabit = coddatabit;
        this.latitude = latitude;
        this.longitude = longitude;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getVersaocode() {
        return versaocode;
    }

    public void setVersaocode(Integer versaocode) {
        this.versaocode = versaocode;
    }

    public String getVersaoname() {
        return versaoname;
    }

    public void setVersaoname(String versaoname) {
        this.versaoname = versaoname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCoddatabit() {
        return coddatabit;
    }

    public void setCoddatabit(String coddatabit) {
        this.coddatabit = coddatabit;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

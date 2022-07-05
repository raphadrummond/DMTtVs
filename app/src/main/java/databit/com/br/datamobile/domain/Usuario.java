package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 15/03/2016.
 */
public class Usuario implements Serializable {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private Integer codigo;
    @DatabaseField
    private String codcli;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String email;
    @DatabaseField
    private String nomeempresa;
    @DatabaseField
    private String login;
    @DatabaseField
    private String senha;
    @DatabaseField
    private String codtec;
    @DatabaseField
    private String ponto;
    @DatabaseField
    private String hrinimanhaseg;
    @DatabaseField
    private String hrfimmanhaseg;
    @DatabaseField
    private String hrinitardeseg;
    @DatabaseField
    private String hrfimtardeseg;
    @DatabaseField
    private String hrinimanhater;
    @DatabaseField
    private String hrfimmanhater;
    @DatabaseField
    private String hrinitardeter;
    @DatabaseField
    private String hrfimtardeter;
    @DatabaseField
    private String hrinimanhaqua;
    @DatabaseField
    private String hrfimmanhaqua;
    @DatabaseField
    private String hrinitardequa;
    @DatabaseField
    private String hrfimtardequa;
    @DatabaseField
    private String hrinimanhaqui;
    @DatabaseField
    private String hrfimmanhaqui;
    @DatabaseField
    private String hrinitardequi;
    @DatabaseField
    private String hrfimtardequi;
    @DatabaseField
    private String hrinimanhasex;
    @DatabaseField
    private String hrfimmanhasex;
    @DatabaseField
    private String hrinitardesex;
    @DatabaseField
    private String hrfimtardesex;
    @DatabaseField
    private Integer altsenha;

    
    public Usuario() {
    }

    public Usuario(String id, Integer codigo, String codcli, String nome, String email, String nomeempresa, String login, String senha, String codtec, String ponto, String hrinimanhaseg, String hrfimmanhaseg, String hrinitardeseg, String hrfimtardeseg, String hrinimanhater, String hrfimmanhater, String hrinitardeter, String hrfimtardeter, String hrinimanhaqua, String hrfimmanhaqua, String hrinitardequa, String hrfimtardequa, String hrinimanhaqui, String hrfimmanhaqui, String hrinitardequi, String hrfimtardequi, String hrinimanhasex, String hrfimmanhasex, String hrinitardesex, String hrfimtardesex, Integer altsenha) {
        this.id = id;
        this.codigo = codigo;
        this.codcli = codcli;
        this.nome = nome;
        this.email = email;
        this.nomeempresa = nomeempresa;
        this.login = login;
        this.senha = senha;
        this.codtec = codtec;
        this.ponto = ponto;
        this.hrinimanhaseg = hrinimanhaseg;
        this.hrfimmanhaseg = hrfimmanhaseg;
        this.hrinitardeseg = hrinitardeseg;
        this.hrfimtardeseg = hrfimtardeseg;
        this.hrinimanhater = hrinimanhater;
        this.hrfimmanhater = hrfimmanhater;
        this.hrinitardeter = hrinitardeter;
        this.hrfimtardeter = hrfimtardeter;
        this.hrinimanhaqua = hrinimanhaqua;
        this.hrfimmanhaqua = hrfimmanhaqua;
        this.hrinitardequa = hrinitardequa;
        this.hrfimtardequa = hrfimtardequa;
        this.hrinimanhaqui = hrinimanhaqui;
        this.hrfimmanhaqui = hrfimmanhaqui;
        this.hrinitardequi = hrinitardequi;
        this.hrfimtardequi = hrfimtardequi;
        this.hrinimanhasex = hrinimanhasex;
        this.hrfimmanhasex = hrfimmanhasex;
        this.hrinitardesex = hrinitardesex;
        this.hrfimtardesex = hrfimtardesex;
        this.altsenha = altsenha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodtec() {
        return codtec;
    }

    public void setCodtec(String codtec) {
        this.codtec = codtec;
    }

    public String getPonto() {
        return ponto;
    }

    public void setPonto(String ponto) {
        this.ponto = ponto;
    }

    public String getHrinimanhaseg() {
        return hrinimanhaseg;
    }

    public void setHrinimanhaseg(String hrinimanhaseg) {
        this.hrinimanhaseg = hrinimanhaseg;
    }

    public String getHrfimmanhaseg() {
        return hrfimmanhaseg;
    }

    public void setHrfimmanhaseg(String hrfimmanhaseg) {
        this.hrfimmanhaseg = hrfimmanhaseg;
    }

    public String getHrinitardeseg() {
        return hrinitardeseg;
    }

    public void setHrinitardeseg(String hrinitardeseg) {
        this.hrinitardeseg = hrinitardeseg;
    }

    public String getHrfimtardeseg() {
        return hrfimtardeseg;
    }

    public void setHrfimtardeseg(String hrfimtardeseg) {
        this.hrfimtardeseg = hrfimtardeseg;
    }

    public String getHrinimanhater() {
        return hrinimanhater;
    }

    public void setHrinimanhater(String hrinimanhater) {
        this.hrinimanhater = hrinimanhater;
    }

    public String getHrfimmanhater() {
        return hrfimmanhater;
    }

    public void setHrfimmanhater(String hrfimmanhater) {
        this.hrfimmanhater = hrfimmanhater;
    }

    public String getHrinitardeter() {
        return hrinitardeter;
    }

    public void setHrinitardeter(String hrinitardeter) {
        this.hrinitardeter = hrinitardeter;
    }

    public String getHrfimtardeter() {
        return hrfimtardeter;
    }

    public void setHrfimtardeter(String hrfimtardeter) {
        this.hrfimtardeter = hrfimtardeter;
    }

    public String getHrinimanhaqua() {
        return hrinimanhaqua;
    }

    public void setHrinimanhaqua(String hrinimanhaqua) {
        this.hrinimanhaqua = hrinimanhaqua;
    }

    public String getHrfimmanhaqua() {
        return hrfimmanhaqua;
    }

    public void setHrfimmanhaqua(String hrfimmanhaqua) {
        this.hrfimmanhaqua = hrfimmanhaqua;
    }

    public String getHrinitardequa() {
        return hrinitardequa;
    }

    public void setHrinitardequa(String hrinitardequa) {
        this.hrinitardequa = hrinitardequa;
    }

    public String getHrfimtardequa() {
        return hrfimtardequa;
    }

    public void setHrfimtardequa(String hrfimtardequa) {
        this.hrfimtardequa = hrfimtardequa;
    }

    public String getHrinimanhaqui() {
        return hrinimanhaqui;
    }

    public void setHrinimanhaqui(String hrinimanhaqui) {
        this.hrinimanhaqui = hrinimanhaqui;
    }

    public String getHrfimmanhaqui() {
        return hrfimmanhaqui;
    }

    public void setHrfimmanhaqui(String hrfimmanhaqui) {
        this.hrfimmanhaqui = hrfimmanhaqui;
    }

    public String getHrinitardequi() {
        return hrinitardequi;
    }

    public void setHrinitardequi(String hrinitardequi) {
        this.hrinitardequi = hrinitardequi;
    }

    public String getHrfimtardequi() {
        return hrfimtardequi;
    }

    public void setHrfimtardequi(String hrfimtardequi) {
        this.hrfimtardequi = hrfimtardequi;
    }

    public String getHrinimanhasex() {
        return hrinimanhasex;
    }

    public void setHrinimanhasex(String hrinimanhasex) {
        this.hrinimanhasex = hrinimanhasex;
    }

    public String getHrfimmanhasex() {
        return hrfimmanhasex;
    }

    public void setHrfimmanhasex(String hrfimmanhasex) {
        this.hrfimmanhasex = hrfimmanhasex;
    }

    public String getHrinitardesex() {
        return hrinitardesex;
    }

    public void setHrinitardesex(String hrinitardesex) {
        this.hrinitardesex = hrinitardesex;
    }

    public String getHrfimtardesex() {
        return hrfimtardesex;
    }

    public void setHrfimtardesex(String hrfimtardesex) {
        this.hrfimtardesex = hrfimtardesex;
    }

    public Integer getAltsenha() {
        return altsenha;
    }

    public void setAltsenha(Integer altsenha) {
        this.altsenha = altsenha;
    }
}

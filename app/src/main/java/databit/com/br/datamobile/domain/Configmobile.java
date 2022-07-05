package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Sidney on 28/03/2018.
 */
public class Configmobile implements Serializable {
    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String obrkmplaca;

    @DatabaseField
    private String pastaftp;

    public Configmobile() {
    }


    public Configmobile(Integer id, String obrkmplaca, String pastaftp) {
        this.id = id;
        this.obrkmplaca = obrkmplaca;
        this.pastaftp = pastaftp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObrkmplaca() {
        return obrkmplaca;
    }

    public void setObrkmplaca(String obrkmplaca) {
        this.obrkmplaca = obrkmplaca;
    }

    public String getPastaftp() {
        return pastaftp;
    }

    public void setPastaftp(String pastaftp) {
        this.pastaftp = pastaftp;
    }


}

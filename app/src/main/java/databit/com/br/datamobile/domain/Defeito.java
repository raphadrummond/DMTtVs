package databit.com.br.datamobile.domain;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by user on 05/04/2016.
 */
public class Defeito {

    @DatabaseField(id = true)
    private String codigo;
    @DatabaseField
    private String nome;

    public Defeito(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Defeito(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

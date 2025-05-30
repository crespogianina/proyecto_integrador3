package org.example.entities;

public class Domicilio {
    private String calle;
    private Integer numero;
    private Integer id;

    Domicilio(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCalle() {
        return calle;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
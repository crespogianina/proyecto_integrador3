package org.example.entities;

public class Domicilio {
    private String calle;
    private Integer numero;
    private Integer id;
    private String localidad;

    public Domicilio(String calle, Integer numero, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
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

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    @Override
    public String toString() {
        return "Domicilio{id=" + id + ", calle='" + calle + "', numero=" + numero + ", localidad='" + localidad + "'}";
    }




}
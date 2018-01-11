package com.example.edwin.appmyevents.interfaz.Modelo;

import java.util.Date;

/**
 * Created by edwin on 10/01/18.
 */

public class Evento {

    private String nombre;
    private String descripcion;
    private String costo;
    private Date fechaEvento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo='" + costo + '\'' +
                ", fechaEvento=" + fechaEvento +
                '}';
    }
}

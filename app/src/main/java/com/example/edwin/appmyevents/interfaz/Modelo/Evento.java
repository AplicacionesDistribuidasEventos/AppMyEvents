package com.example.edwin.appmyevents.interfaz.Modelo;

import java.util.Date;

/**
 * Created by edwin on 10/01/18.
 */

public class Evento {

    private int codigo;
    private String nombre;
    private String descripcion;
    private String costo;
    private String fechaEvento;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo='" + costo + '\'' +
                ", fechaEvento='" + fechaEvento + '\'' +
                '}';
    }
}

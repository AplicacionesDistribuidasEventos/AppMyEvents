package com.example.edwin.appmyevents.interfaz.Modelo;

/**
 * Created by sesla on 11/1/2018.
 */

public class Local {

    private String nombre;
    private String descripcion;
    private String capacidad;
    private String costo;

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

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Local{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", capacidad='" + capacidad + '\'' +
                ", costo='" + costo + '\'' +
                '}';
    }
}

package com.example.edwin.appmyevents.interfaz.Modelo;

/**
 * Created by edwin on 05/01/18.
 */

public class Categoria {

    private String nombre;
    private String descipcion;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    @Override
    public String toString() {
        return "CategoriaActivity{" +
                "nombre='" + nombre + '\'' +
                ", descipcion='" + descipcion + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

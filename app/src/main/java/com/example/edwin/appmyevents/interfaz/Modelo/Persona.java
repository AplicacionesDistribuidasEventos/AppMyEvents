package com.example.edwin.appmyevents.interfaz.Modelo;

/**
 * Created by edwin on 06/01/18.
 */

public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String contrasenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String setApellido(String apellido) {
        this.apellido = apellido;
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String setCorreo(String correo) {
        this.correo = correo;
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}//fin clase Persona
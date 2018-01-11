package com.example.edwin.appmyevents.interfaz.Modelo;

/**
 * Created by edwin on 08/01/18.
 */

public class Login {

    private int id;
    private String correo;
    private String contrasenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}

package com.example.edwin.appmyevents.interfaz.Utilidades;

import android.view.View;
import android.widget.Toast;

import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Persona;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;

/**
 * Created by sesla on 11/1/2018.
 */

public class Actualizar implements OnTaskCompleted{
    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;


    public void actualizarUsuario(String nombre, String apellido, String correo){
        clienteRest = new ClienteRest(this);
        System.out.println("NOMBRE NUEVOOOOOOO: "+nombre);
        try {
            String url = "http://192.168.0.102:8080/MyEvents/rs/usuarios/actualizar-usuario?nombre="+nombre+"&apellido="+apellido+"&correo="+correo+"&id="+ LoginActivity.cod_per;
            clienteRest.doGet(url,null,WS_CONSULTA,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                Respuesta r = new Respuesta();
                r = clienteRest.getResult(Respuesta.class);
                System.out.println("MENSAJE RRRRRRRRRRRR:"+r.getMensaje());

            }
        }
    }

}

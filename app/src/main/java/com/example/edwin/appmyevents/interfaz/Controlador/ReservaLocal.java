package com.example.edwin.appmyevents.interfaz.Controlador;

import android.content.Context;
import android.view.View;

import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;


public class ReservaLocal implements OnTaskCompleted{

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    Context context;

    public ReservaLocal(int id_local, String fecha){
        definirEstado(id_local, fecha);
    }

    private void definirEstado(int id_local, String fecha){
        clienteRest = new ClienteRest(this);
        try {
            System.out.println("CODIGO PERSONA:  "+ LoginActivity.cod_per);
            String url = "http://"+LoginActivity.dir_ip+"/MyEvents/rs/locales/crear-reservas?id_u="+LoginActivity.cod_per+"&id_l="+id_local+"&fecha_e="+ fecha;
            clienteRest.doGet(url, null,WS_CONSULTA,true);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud== WS_CONSULTA){
            if(!clienteRest.isCancelled()){
                Respuesta r = (Respuesta) clienteRest.getResult(Respuesta.class);
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}

package com.example.edwin.appmyevents.interfaz.Controlador;


//import com.cuenca.myevents.testscategoriaeventos_asistenciaeventos.Modelo.Respuesta;
//import com.cuenca.myevents.testscategoriaeventos_asistenciaeventos.Utilidades.ClienteRest;
//import com.cuenca.myevents.testscategoriaeventos_asistenciaeventos.Utilidades.OnTaskCompleted;
import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
//import com.google.android.gms.common.api.GoogleApiClient;


public class Confirmar_Asistencia implements OnTaskCompleted{
    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;

    //private GoogleApiClient client;

    public Confirmar_Asistencia(int id_evento, String estado){
        definirEstado(id_evento, estado);
    }

    private void definirEstado(int id_evento, String estado){
        clienteRest = new ClienteRest(this);
        try {
            System.out.println("CODIGO PERSONA:  "+LoginActivity.cod_per);
            String url = "http://"+LoginActivity.dir_ip+":8080/MyEvents/rs/eventos/establecer-asistencia?estado="+estado+"&cod="+id_evento+"&id_user="+ LoginActivity.cod_per;
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
}

package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.EventoConslAdapter;
import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.MainActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;

public class ConsultaAsistencias extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener {


    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;

    private ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_asistencias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listAsistencia);


        listAsistencias();
        context = this;
    }


    @Override
    public void onClick(View view) {

    }

    public void listAsistencias( ){
        clienteRest = new ClienteRest(this);

        try {
            String url1 = "http://"+ LoginActivity.dir_ip+":8080/MyEvents/rs/eventos/reporte-Aeventos?id_usuario="+ LoginActivity.cod_per;
            clienteRest.doGet(url1, null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Evento> listEveCon = (ArrayList<Evento>) clienteRest.getResultList(Evento.class);
                EventoConslAdapter conslAdapter = new EventoConslAdapter(listEveCon, context);
                listView.setAdapter(conslAdapter);
            }
        }

    }

    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

}

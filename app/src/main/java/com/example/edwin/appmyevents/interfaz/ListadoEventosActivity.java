package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.EventoAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;

public class ListadoEventosActivity extends AppCompatActivity implements OnTaskCompleted, ListView.OnItemClickListener {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    Context context;
    ListView listViewEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_eventos);
        Intent intent = getIntent();
        context = this;
        int idCategoria = intent.getIntExtra("id", 0);
        listViewEvento = findViewById(R.id.listViewEventos);
        if (idCategoria > 0) {
            listarEvento(idCategoria);
        } else {
            Toast.makeText(context, "OOhhhh lo sentimos Profe, se produjo un error :( ", Toast.LENGTH_SHORT).show();
        }



    }

    public void listarEvento(int idCategoria) {
        clienteRest = new ClienteRest(this);
        try {
            String url2 = "http://192.168.0.102:8080/MyEvents/rs/eventos/listado-categoria-eventos?id_categoria=" + idCategoria;
            clienteRest.doGet(url2, null, WS_CONSULTA, true);
        } catch (Exception e) {
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Evento> eventos = (ArrayList<Evento>) clienteRest.getResultList(Evento.class);
                EventoAdapter eventoAdapter = new EventoAdapter(eventos, context);
                listViewEvento.setAdapter(eventoAdapter);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String valor = (String) adapterView.getItemAtPosition(i);
        Intent muestraEventos = new Intent(this, MuestraEvento.class);
        muestraEventos.putExtra("Mensaje", valor);
        startActivity(muestraEventos);
    }

    /**
     * Permite mostrar un mensaje Toast en pantalla,
     *
     * @param mensaje Texto del mensaje a mostrar
     */
    private void showMensaje(String mensaje) {
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

}
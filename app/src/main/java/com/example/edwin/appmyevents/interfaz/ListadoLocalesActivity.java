package com.example.edwin.appmyevents.interfaz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.CategoriaAdapter;
import com.example.edwin.appmyevents.adapters.LocalAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
import com.squareup.picasso.Picasso;


import java.util.AbstractQueue;
import java.util.ArrayList;

public class ListadoLocalesActivity extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;

    private ListView listView;
    Context context;
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_locales);

        //listaLocales(new ArrayList<Local>());

        //ListView list11=(ListView) findViewById(R.id.listViewLocal);


        /////////////////
        listView = findViewById(R.id.listViewLocal);
        listaLocales(new ArrayList<Local>());
        context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Local local = (Local) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(context, ListadoLocalesActivity.class);
                intent.putExtra("id", local.getCodigo());
                startActivity(intent);
            }
        });


        ////////////////

        //list11.setOnItemClickListener(this);
        //listarEvento(new ArrayList<Evento>());


        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    @Override
    public void onClick(View view) {

    }

    public void listaLocales(ArrayList<Local> locals ){
        clienteRest = new ClienteRest(this);
        Local local = new Local();
        try {

            String url1 = "http://192.168.0.102:8080/MyEvents/rs/locales/listado-locales";
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
                ArrayList<Local> locals = (ArrayList<Local>) clienteRest.getResultList(Local.class);
                LocalAdapter localAdapter = new LocalAdapter(locals, context);
                listView.setAdapter(localAdapter);
            }
        }

    }

    /**
     * Permite mostrar un mensaje Toast en pantalla,
     * @param mensaje    Texto del mensaje a mostrar
     */
    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }
}

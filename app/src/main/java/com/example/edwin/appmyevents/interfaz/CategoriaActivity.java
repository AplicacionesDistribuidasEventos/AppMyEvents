package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.CategoriaAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class CategoriaActivity extends AppCompatActivity implements OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    private ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        //Toolbar toolbarCategoria = findViewById(R.id.toolbarCatoria);
        //setSupportActionBar(toolbarCategoria);
        listView = findViewById(R.id.listView);
        listarCategoria(new ArrayList<Categoria>());
        context = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Categoria categoria = (Categoria) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(context, ListadoEventosActivity.class);
                intent.putExtra("id", categoria.getId());
                startActivity(intent);
            }
        });
    }

    private void listarCategoria(ArrayList<Categoria> categorias) {

        clienteRest = new ClienteRest(this);
        try {
            //String url = "http://192.168.0.102:8080/MyEvents/rs/usuarios/listado-categorias";
            String url = "http://"+LoginActivity.dir_ip+"/MyEvents/rs/usuarios/listado-categorias";
            clienteRest.doGet(url, null, WS_CONSULTA, true);
        } catch (Exception e) {
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) clienteRest.getResultList(Categoria.class);
                CategoriaAdapter categoriaAdapter = new CategoriaAdapter(categorias, context);
                listView.setAdapter(categoriaAdapter);
            }
        }
    }

    @Override
    public void onClick(View view) {

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

}//fin de la clase categoria

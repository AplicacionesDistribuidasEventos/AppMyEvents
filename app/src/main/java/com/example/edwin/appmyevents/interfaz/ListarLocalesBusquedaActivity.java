package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.LocalBusquedaAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

public class ListarLocalesBusquedaActivity extends AppCompatActivity implements OnTaskCompleted{

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    ListView listViewLocales;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locales_busqueda);

        //context = this;

        listViewLocales = (ListView) findViewById(R.id.listViewLocalBusqueda);
        listarLocalBusqueda();

    }

    private void listarLocalBusqueda() {
        clienteRest = new ClienteRest(this);
        try {
            String url = "http://"+LoginActivity.dir_ip+":8080/MyEvents/rs/locales/listado-locales-fecha?fecha_SR="+LocalesActivity.fec;
            System.out.println("Datos"+LocalesActivity.fec);
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
                ArrayList<Local> locales = (ArrayList<Local>) clienteRest.getResultList(Local.class);
                LocalBusquedaAdapter localBusquedaAdapter = new LocalBusquedaAdapter(locales, this);
                listViewLocales.setAdapter(localBusquedaAdapter);
            }
        }

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

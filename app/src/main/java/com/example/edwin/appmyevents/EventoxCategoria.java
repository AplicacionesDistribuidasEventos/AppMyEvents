package com.example.edwin.appmyevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class EventoxCategoria extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener, ListView.OnItemClickListener{
    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventox_categoria);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void listaCategoriaxEvento(int id){
        clienteRest = new ClienteRest(this);
        try{
            System.out.println("LISTADO CATEGORIA EVENTO");
            String url = "http://"+ LoginActivity.dir_ip+":8080/MyEvents/rs/eventos/listado-categoria-eventos?id_categoria="+id;
            clienteRest.doGet(url, null,WS_CONSULTA,true);
        }catch (Exception e){
            //showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {

        ListView list1 = (ListView) findViewById(R.id.listViewEventos);
        ArrayList<String> arrayList = new ArrayList<String>();

        String ayudaTexto = " ";
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Evento> eventos = (ArrayList<Evento>) clienteRest.getResultList(Evento.class);

                for (int i = 0; i < eventos.size(); i++) {
                    ayudaTexto = "\n" + eventos.get(i).getNombre().toString().toUpperCase() + "\n" + "Descripcion: " + eventos.get(i).getDescripcion()+"\n"+"Costo: "+eventos.get(i).getCosto()+"\n"+"Fecha: "+eventos.get(i).getFechaEvento();
                    System.out.println("REGISTROS " + ayudaTexto);
                    System.out.println(i);
                    arrayList.add(i, ayudaTexto);

                }
                showMensaje("Total de Eventos " + eventos.size());

            }//fin if
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_black_text_evento, arrayList);
            list1.setAdapter(adapter1);
            list1.getDivider();
            list1.getCacheColorHint();
            list1.getBaseline();
            adapter1.notifyDataSetChanged();
            System.out.println("Solicitud  " + idSolicitud);

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

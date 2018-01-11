package com.example.edwin.appmyevents.interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ListadoLocalesActivity extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_locales);

        listaLocales(new ArrayList<Local>());

        ListView list11=(ListView) findViewById(R.id.listViewLocal);
        //list11.setOnItemClickListener(this);
        //listarEvento(new ArrayList<Evento>());


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    @Override
    public void onClick(View view) {

    }

    public void listaLocales(ArrayList<Local> locals ){
        clienteRest = new ClienteRest(this);

        try {

            String url1 = "http://192.168.0.101:8080/MyEvents/rs/locales/listado-locales";
            clienteRest.doGet(url1, null,WS_CONSULTA,true);

        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        ListView list1=(ListView) findViewById(R.id.listViewLocal);
        ArrayList<String> arrayList =new ArrayList<String>();

        String ayudaTexto=" ";
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Local> locals = (ArrayList<Local>) clienteRest.getResultList(Local.class);

                for (int i = 0; i < locals.size(); i++) {
                    ayudaTexto= "\n"+locals.get(i).getNombre().toString().toUpperCase()+"\n"+"Descripcion:"+locals.get(i).getDescripcion()+"\n";
                    System.out.println("REGISTROS "+ayudaTexto);
                    System.out.println(i);
                    arrayList.add(i,ayudaTexto);

                }
                showMensaje("Total de Locales " + locals.size());

            }//fin if
            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_black_text_local,arrayList);
            list1.setAdapter(adapter1);
            list1.getDivider();
            list1.getCacheColorHint();
            list1.getBaseline();
            adapter1.notifyDataSetChanged();
            System.out.println("Solicitud  "+idSolicitud);
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

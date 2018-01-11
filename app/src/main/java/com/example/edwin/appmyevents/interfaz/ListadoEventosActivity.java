package com.example.edwin.appmyevents.interfaz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


public class ListadoEventosActivity extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener, ListView.OnItemClickListener {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_eventos);

        listarCategoria(new ArrayList<Categoria>());

        ListView list11=(ListView) findViewById(R.id.listViewEventos);
        list11.setOnItemClickListener(this);
        //listarEvento(new ArrayList<Evento>());

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onClick(View view) {

    }

    public void listarCategoria(ArrayList<Categoria> categorias){
        clienteRest = new ClienteRest(this);

        try {

            String url1 = "http://192.168.0.101:8080/MyEvents/rs/categoria/listado-categorias";
            String url2 = "http://192.168.0.101:8080/MyEvents/rs/eventos/listado-eventos";
            clienteRest.doGet(url2, null,WS_CONSULTA,true);

        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }

    }

    public void listarEvento(ArrayList<Evento> eventos){
        clienteRest = new ClienteRest(this);

        try {

            String url2 = "http://192.168.0.101:8080/MyEvents/rs/eventos/listado-categoria-eventos?id_categoria=";
            clienteRest.doGet(url2, null,WS_CONSULTA,true);
            //clienteRest.doGet(url2, null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }


    @Override
    public void onTaskCompleted(int idSolicitud) {
        ListView list1=(ListView) findViewById(R.id.listViewEventos);
        ArrayList<String> arrayList =new ArrayList<String>();

        String ayudaTexto=" ";
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) clienteRest.getResultList(Categoria.class);

                for (int i = 0; i < categorias.size(); i++) {
                    ayudaTexto= "\n"+categorias.get(i).getNombre().toString().toUpperCase()+"\n"+"Descripcion:"+categorias.get(i).getDescipcion()+"\n";
                    System.out.println("REGISTROS "+ayudaTexto);
                    System.out.println(i);
                    arrayList.add(i,ayudaTexto);

                }
                showMensaje("Total de Eventos " + categorias.size());

            }//fin if
            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_black_text_evento,arrayList);
            list1.setAdapter(adapter1);
            list1.getDivider();
            list1.getCacheColorHint();
            list1.getBaseline();
            adapter1.notifyDataSetChanged();
            System.out.println("Solicitud  "+idSolicitud);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String valor= (String) adapterView.getItemAtPosition(i);
        Intent muestraEventos = new Intent(this,MuestraEvento.class);
        muestraEventos.putExtra("Mensaje",valor);
        startActivity(muestraEventos);
    }

    /**
     * Permite mostrar un mensaje Toast en pantalla,
     * @param mensaje    Texto del mensaje a mostrar
     */
    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

}//fin clase listado Eventos

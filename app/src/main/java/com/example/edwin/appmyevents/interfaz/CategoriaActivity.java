package com.example.edwin.appmyevents.interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class CategoriaActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted{

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1 ;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        //Button btn1 = (Button) findViewById(R.id.btnListadoCategoria);
        //btn1.setOnClickListener(this);

        Toolbar toolbarCategoria = (Toolbar) findViewById(R.id.toolbarCatoria);
        //setSupportActionBar(toolbarCategoria);
        listarCategoria(new ArrayList<Categoria>());
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onClick(View view) {
       /* switch (view.getId()){
            case R.id.btnListadoCategoria:
                listarCategoria(new ArrayList<Categoria>());
                break;
            default:
                break;
        }*/
    }

    private void listarCategoria(ArrayList<Categoria> categorias) {
        clienteRest = new ClienteRest(this);

        try {
            //String url = "http://192.168.0.102:8080/MyEvents/rs/usuarios/listado-categorias";
            String url = "http://192.168.1.15:8080/MyEvents/rs/categoria/listado-categorias";
            clienteRest.doGet(url, null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }

    }

    @Override
    public void onTaskCompleted(int idSolicitud) {

        ListView list=(ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList =new ArrayList<String>();

        String ayudaTexto=" ";
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Categoria> empresass = (ArrayList<Categoria>) clienteRest.getResultList(Categoria.class);

                for (int i = 0; i < empresass.size(); i++) {
                    ayudaTexto= "\n"+empresass.get(i).getNombre().toString().toUpperCase()+"\n"+"Descripcion:"+empresass.get(i).getDescipcion()+"\n";
                    System.out.println("REGISTROS "+ayudaTexto);

                    System.out.println(i);

                    arrayList.add(i,ayudaTexto);

                }

                showMensaje("Total de Categorias " + empresass.size());
                // tv.setText(ayudaTexto);
            }//fin if
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_black_text,arrayList);
            list.setAdapter(adapter);
            list.getDivider();
            //boolean qc=list.getItemsCanFocus();
            list.getCacheColorHint();
            list.getBaseline();
            adapter.notifyDataSetChanged();
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

}//fin de la clase categoria

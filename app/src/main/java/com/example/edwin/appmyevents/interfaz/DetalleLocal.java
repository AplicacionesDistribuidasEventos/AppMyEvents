package com.example.edwin.appmyevents.interfaz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.MapaLocal;
import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.ComentarioAdapter;
import com.example.edwin.appmyevents.adapters.LocalAdapter;
import com.example.edwin.appmyevents.interfaz.Controlador.ReservaLocal;
import com.example.edwin.appmyevents.interfaz.Modelo.Comentario;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DetalleLocal extends AppCompatActivity implements OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    private int WS_GUARDAR = 2;
    LinearLayout rating;
    CheckBox estrella;
    Context context;

    private int codigo;
    private String nombre;
    private String descripcion;
    private String capacidad;
    private String costo;
    private String fotoPerfil;
    private String telefono;

    ImageView fotolocaldetalle;
    TextView nombredetalle, descripciondetalle, capacidadetalle, costodetalle;
    Button llamada, reserva, comentarioT;
    ListView listadetalle;
    EditText etComentario;
    Button btnmapa;

    /**
     * VARIABLES UTILIZADAS PARA LOCALIZACION*/
    public static String latitud;
    public static String longitud;
    public static String nlocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local);
        context = this;
        fotolocaldetalle = findViewById(R.id.fotolocaldetalle);
        nombredetalle = findViewById(R.id.nombredetalle);
        descripciondetalle = findViewById(R.id.descripciondetalle);
        capacidadetalle = findViewById(R.id.capacidadetalle);
        costodetalle = findViewById(R.id.costodetalle);
        llamada = findViewById(R.id.llamada);
        reserva = findViewById(R.id.reserva);
        listadetalle = findViewById(R.id.listadetalle);
        etComentario = findViewById(R.id.etComentario);
        btnmapa = findViewById(R.id.btnmapa);

        comentarioT = findViewById(R.id.guardaComentario);

        rating = findViewById(R.id.ratings);
        for (int i = 1; i <= 5; i++) {
            estrella = rating.findViewWithTag(String.valueOf(i));
            estrella.setOnClickListener(estrellaLectura);
        }

        Intent intent = getIntent();

        codigo = intent.getIntExtra("codigo", 0);
        nombre = intent.getStringExtra("nombre");
        nlocal = nombre;
        descripcion = intent.getStringExtra("descripcion");
        capacidad = intent.getStringExtra("capacidad");
        costo = intent.getStringExtra("costo");
        fotoPerfil = intent.getStringExtra("fotoPerfil");
        telefono = intent.getStringExtra("telefono");
        latitud = intent.getStringExtra("latitud");
        longitud = intent.getStringExtra("longitud");

        Picasso.with(context).load("http://" + LoginActivity.dir_ip + "/MyEvents/" + fotoPerfil).fit().centerInside().into(fotolocaldetalle);
        nombredetalle.setText(nombre);
        descripciondetalle.setText(descripcion);
        capacidadetalle.setText(capacidad + "");
        costodetalle.setText(costo + "");

        ListarComentarios();

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Local l = new Local();
                System.out.println("LAMADAAAAAAAAAAAAAAA "+telefono);
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                    return;
                    context.startActivity(i);

            }
        });


        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Local local1 = view.geti
                Toast.makeText(context,"Reserva Satisfactoria..!!!:"+codigo,Toast.LENGTH_SHORT).show();
                String fecha = ListarLocalesBusquedaActivity.fec;
                ReservaLocal res = new ReservaLocal(codigo, fecha);
            }
        });


        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"Cargando ubicacion de: "+nlocal,Toast.LENGTH_SHORT).show();

                Intent iubicacion = new Intent(context, MapaLocal.class);
                //view.getContext().startActivity(iubicacion);
                startActivity(iubicacion);
            }
        });
       comentarioT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GuardarComentario(view);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void GuardarComentario(View view) throws UnsupportedEncodingException {
        String comentario = etComentario.getText().toString();
        comentario = URLEncoder.encode(comentario, "UTF-8");
        clienteRest = new ClienteRest(this);
        try {
            String url = "http://" + LoginActivity.dir_ip + ":8080/MyEvents/rs/Comentarios/agregar-comentario-local?id_local=" + codigo + "&loc_descripcion=" + comentario + "&id_user=" + LoginActivity.cod_per;
            System.out.println("URL :  ==> " + url);
            clienteRest.doGet(url, null, WS_GUARDAR, true);
        } catch (Exception e) {
            showMensaje("Error Consulta");
            e.printStackTrace();
        }

        // TODO validad que no sea null

        // TODO guardar comentario


    }

    private View.OnClickListener estrellaLectura = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = Integer.valueOf((String) view.getTag());
            for (int i = 1; i <= 5; i++) {
                estrella = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                estrella.setChecked(true);
            }
            for (int i = tag + 1; i <= 5; i++) {
                estrella = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                estrella.setChecked(false);
            }
        }
    };


    private void ListarComentarios() {
        clienteRest = new ClienteRest(this);
        try {
            String url = "http://" + LoginActivity.dir_ip + ":8080/MyEvents/rs/Comentarios/list-comentarios-local?id_local=" + codigo;
            System.out.println("URL :  ==> " + url);
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
                ArrayList<Comentario> comentarios = (ArrayList<Comentario>) clienteRest.getResultList(Comentario.class);
                ComentarioAdapter localBusquedaAdapter = new ComentarioAdapter(comentarios, context);
                listadetalle.setAdapter(localBusquedaAdapter);

            }
        }else if(idSolicitud == WS_GUARDAR){
            if(!clienteRest.isCancelled()){
                Respuesta r = new Respuesta();
                r = clienteRest.getResult(Respuesta.class);
                Toast toast = Toast.makeText(this,r.getMensaje(), Toast.LENGTH_SHORT);
                toast.show();
                System.out.println("GRABANDO COMENTARIO");
                etComentario.setText("");

                /**LISTA LOS COMENTARIOS RECIENTES
                 * */
                ListarComentarios();
            }
        }
    }

    private void showMensaje(String mensaje) {
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guardaComentario:
                try {
                    GuardarComentario(view);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                Intent intent2 = new Intent(this, Ingreso.class);
//                startActivity(intent2);

                Context context = getApplicationContext();
                CharSequence text = "Comentario Posteado";
                int duracion = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duracion);
                toast.show();
                break;
        }

    }
}
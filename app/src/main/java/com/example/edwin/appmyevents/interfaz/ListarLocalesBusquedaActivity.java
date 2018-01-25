package com.example.edwin.appmyevents.interfaz;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.LocalBusquedaAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListarLocalesBusquedaActivity extends AppCompatActivity implements OnTaskCompleted{

    private int WS_CONSULTA = 1;
    ListView listViewLocales;
    Context context;


    TextView tvFecha;
    Calendar onCurrentDatos;
    private DatePicker calendario;
    int dia, mes, anio;
    private ListView listViewBusqueda;
    Button bton ;
    EditText txtBusqueda;
    ClienteRest clienteRest;

    public static String fec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locales_busqueda);
        context = this;
        tvFecha= findViewById(R.id.tvFecha);
        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                        mes=mes+1;
                        tvFecha.setText(anio+"-"+mes+"-"+dia);
                    }

                }, anio,mes, dia);
                datePickerDialog.show();
            }
        });
        listViewLocales = findViewById(R.id.listViewLocalBusqueda);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        tvFecha.setText(simpleDateFormat.format(new Date()));
        /**SETEO LA FECHA
         * */
        fec = tvFecha.getText().toString();
    }

    private void listarLocalBusqueda() {
        clienteRest = new ClienteRest(this);
        try {
            String url = "http://"+LoginActivity.dir_ip+":8080/MyEvents/rs/locales/listado-locales-fecha?fecha_SR="+tvFecha.getText();
            clienteRest.doGet(url, null, WS_CONSULTA, true);
        } catch (Exception e) {
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }


    public void Buscar(View view){

        listarLocalBusqueda();
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                ArrayList<Local> locales = (ArrayList<Local>) clienteRest.getResultList(Local.class);
                LocalBusquedaAdapter localBusquedaAdapter = new LocalBusquedaAdapter(locales, this);
                listViewLocales.setAdapter(localBusquedaAdapter);

                listViewLocales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Local local = (Local) adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(context, DetalleLocal.class);
                        intent.putExtra("codigo", local.getCodigo());
                        intent.putExtra("nombre", local.getNombre());
                        intent.putExtra("descripcion", local.getDescripcion());
                        intent.putExtra("capacidad", local.getCapacidad());
                        intent.putExtra("costo", local.getCosto());
                        intent.putExtra("fotoPerfil", local.getFotoPerfil());
                        intent.putExtra("telefono", local.getTelefono());
                        intent.putExtra("latitud", local.getLatitud());
                        intent.putExtra("longitud", local.getLongitud());

                        startActivity(intent);
                    }
                });

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
}

package com.example.edwin.appmyevents.interfaz;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.CategoriaAdapter;
import com.example.edwin.appmyevents.adapters.LocalBusquedaAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;
import java.util.Calendar;

public class LocalesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Calendar onCurrentDatos;
    private DatePicker calendario;
    int dia, mes, anio;
    Context context;
    private ListView listViewBusqueda;
    Button bton ;
    EditText txtBusqueda;
    ClienteRest clienteRest;

    public static String fec;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locales);

        Button btnRegres = (Button) findViewById(R.id.btnRegresar2);
        btnRegres.setOnClickListener(this);

        bton = (Button) findViewById(R.id.listar);
        bton.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.textView16);
        onCurrentDatos = Calendar.getInstance();

        dia = onCurrentDatos.get(Calendar.DAY_OF_MONTH);
        mes = onCurrentDatos.get(Calendar.MONTH);
        anio = onCurrentDatos.get(Calendar.YEAR);

        mes = mes+1;
        fec = anio+"-"+mes+"-"+dia;
        System.out.println("ESTATICAA FEC LOCA:    "+fec);
        tv.setText(anio+"-"+mes+"-"+dia);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LocalesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                        mes=mes+1;
                        tv.setText(anio+"-"+mes+"-"+dia);
                    }

                }, anio,mes, dia);
                datePickerDialog.show();

            }

        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.listar:
                Intent intent1 = new Intent(this,ListarLocalesBusquedaActivity.class);
                startActivity(intent1);
                break;
            default:
                break;

        }

    }
}

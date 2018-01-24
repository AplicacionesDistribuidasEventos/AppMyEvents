package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.adapters.LocalAdapter;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;

public class DetalleLocal extends AppCompatActivity {

    private ClienteRest clienteRest;
    private int WS_CONSULTA=1;
    LinearLayout rating;
    CheckBox estrella;
    private ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_local);

        listView = (ListView) findViewById(R.id.listadetalle);
        rating = (LinearLayout) findViewById(R.id.ratings);
        for(int i=1; i<=5; i++){
            estrella=(CheckBox) rating.findViewWithTag(String.valueOf(i));
            estrella.setOnClickListener(estrellaLectura);
        }


    }

    private View.OnClickListener estrellaLectura = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = Integer.valueOf((String)view.getTag());
            for (int i=1; i<=5; i++){
                estrella = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                estrella.setChecked(true);
            }
            for (int i=tag+1; i<=5; i++){
                estrella = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                estrella.setChecked(false);
            }
        }
    };


}

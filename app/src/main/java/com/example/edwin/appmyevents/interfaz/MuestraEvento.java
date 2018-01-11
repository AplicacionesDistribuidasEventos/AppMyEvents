package com.example.edwin.appmyevents.interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edwin.appmyevents.R;

public class MuestraEvento extends AppCompatActivity {

    private TextView txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_evento);

        txtMensaje= (TextView) findViewById(R.id.txtMensaje);
        Bundle parametro = getIntent().getExtras();

        if (parametro != null){
            txtMensaje.setText(parametro.getString("Mensaje"));
        }
    }
}

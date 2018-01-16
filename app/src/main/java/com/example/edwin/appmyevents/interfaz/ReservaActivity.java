package com.example.edwin.appmyevents.interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

public class ReservaActivity extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTaskCompleted(int idSolicitud) {

    }
}

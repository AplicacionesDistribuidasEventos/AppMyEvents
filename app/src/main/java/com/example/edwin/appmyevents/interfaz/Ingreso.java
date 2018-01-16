package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.edwin.appmyevents.R;

public class Ingreso extends AppCompatActivity implements View.OnClickListener{


    SharedPreferences prefs ;
    Context contex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        Button btnLoginPrincipal = (Button) findViewById(R.id.btnLoginPrincipal);
        btnLoginPrincipal.setOnClickListener(this);

        Button btnRegistroPrincipal = (Button) findViewById(R.id.btnRegistroPrincipal);
        btnRegistroPrincipal.setOnClickListener(this);
/*
        Button btnImagen = (Button) findViewById(R.id.btnImagenes);
        btnImagen.setOnClickListener(this);
*/
        ////inicio
/*
        contex= this;
        prefs=  getSharedPreferences("eventos", Context.MODE_PRIVATE);
        boolean isLogin= prefs.getBoolean("login", false);

        if(isLogin){
            startActivity(new Intent(contex,MainActivity.class));
        }
*/
        ////akiiii

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginPrincipal:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRegistroPrincipal:
                Intent intent2 = new Intent(this,RegistroActivity.class);
                startActivity(intent2);
                break;
/*
            case R.id.btnImagenes:
                Intent intent3 = new Intent(this,ListadoImagen.class);
                startActivity(intent3);
                break;
*/
            default:
                break;

        }
    }
}

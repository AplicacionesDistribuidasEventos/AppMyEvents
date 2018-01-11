package com.example.edwin.appmyevents.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.edwin.appmyevents.R;

public class Ingreso extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        Button btnLoginPrincipal = (Button) findViewById(R.id.btnLoginPrincipal);
        btnLoginPrincipal.setOnClickListener(this);

        Button btnRegistroPrincipal = (Button) findViewById(R.id.btnRegistroPrincipal);
        btnRegistroPrincipal.setOnClickListener(this);

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
            default:
                break;

        }
    }
}

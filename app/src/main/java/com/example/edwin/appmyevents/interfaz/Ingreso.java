package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.edwin.appmyevents.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Ingreso extends AppCompatActivity implements View.OnClickListener{


    SharedPreferences prefs ;
    Context contex;
    LoginButton login_button;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
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

        initializeControls();
        loginConFB();
    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton) findViewById(R.id.login_button);
    }

    private void loginConFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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

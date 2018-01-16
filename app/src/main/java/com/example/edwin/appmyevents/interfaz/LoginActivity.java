package com.example.edwin.appmyevents.interfaz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Login;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_INGRESA = 1;
    private Login login = new Login();
    EditText correlogin;
    EditText passwordlogin;
    public static int cod_per;
    SharedPreferences prefs ;
    Context contex;
    /**
     * DIRECCION IP QUE SE VA A ESTABLECER EN TODOS LOS WS
     * */
    public static String dir_ip = "172.16.223.191";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnIngresar);
        btnLogin.setOnClickListener(this);
        correlogin = (EditText) findViewById(R.id.txtCorreoLogin);
        passwordlogin = (EditText) findViewById(R.id.txtPasswordLogin);
        contex = this;
        prefs=  getSharedPreferences("eventos", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIngresar:
                if (correlogin.getText().toString().isEmpty()|| passwordlogin.getText().toString().isEmpty())
            {
                showMensaje("por favor llenar todos los campos");
            }
            else {
                    loginUsuario();
                }
                break;

            default:
                break;

        }

    }

    private void loginUsuario() {
        clienteRest = new ClienteRest(this);

        try {
            String url = "http://"+dir_ip+":8080/MyEvents/rs/usuarios/login";

            Login l = new Login();

                l.setCorreo(((EditText) findViewById(R.id.txtCorreoLogin)).getText().toString());
                l.setContrasenia(((EditText) findViewById(R.id.txtPasswordLogin)).getText().toString());

            clienteRest.doPost(url,l,WS_INGRESA,true);
        }catch (Exception e){

            e.printStackTrace();
            showMensaje("Error Logeo");
/*
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("login", false);
            editor.commit();
*/
        }

    }

    @Override
    public void onTaskCompleted(int idSolicitud) {

        System.out.println("Si entra aqui");

        String cadenaNombre1,cadenaNombre2;
        String cadenaPassword1,cadenaPassword2;
        EditText txtCorreoL=(EditText) findViewById(R.id.txtCorreoLogin);
        EditText txtPasswordL=(EditText) findViewById(R.id.txtPasswordLogin);

        ArrayList<Login> usuario = (ArrayList<Login>) clienteRest.getResultList(Login.class);
        for (int i = 0; i < usuario.size(); i++) {
            cadenaNombre1=txtCorreoL.getText().toString();
            cadenaNombre2=usuario.get(i).getCorreo().toString();

            cadenaPassword1=txtPasswordL.getText().toString();
            cadenaPassword2=usuario.get(i).getContrasenia().toString();

            if ((cadenaNombre1.equals(cadenaNombre2))  && (cadenaPassword1.equals(cadenaPassword2)))
            {
                cod_per = usuario.get(0).getId();
                showMensaje("Ingreso Exitoso !!! ");
                System.out.println("USUARIO EXISTE");


                ///ling mantiene
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("login", true);
                editor.commit();
                ///fin login

                Intent i2 = new Intent(this,MainActivity.class);
                startActivity(i2);

            }

            System.out.println(i);
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

}//fin clase LoginActivity

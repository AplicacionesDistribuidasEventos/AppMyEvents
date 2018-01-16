package com.example.edwin.appmyevents.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import com.example.edwin.appmyevents.interfaz.Modelo.Persona;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.List;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_GUARDAR = 1;
    private Persona persona = new Persona();

    EditText nombre;
    EditText apellido;
    EditText cedula;
    EditText correo;
    EditText contrasenia;
    //TextView txtnombre;
    //EditText contrasenia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnRegres = (Button) findViewById(R.id.btnRegresar2);
        btnRegres.setOnClickListener(this);

        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        cedula = (EditText) findViewById(R.id.txtCedula);
        correo = (EditText) findViewById(R.id.txtCorreo);
        contrasenia = (EditText) findViewById(R.id.txtPassword);
        //txtnombre = (TextView) findViewById(R.id.txtNombre):
        //contrasenia2 = (EditText) findViewById(R.id.txtPassword2);

        Button btnRegistro = (Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistro:
                if(nombre.getText().toString().isEmpty())
                {
                    showMensaje("por favor ingrese su nombre");
                }
                else if (apellido.getText().toString().isEmpty()) {
                    showMensaje("por favor ingrese su apellido");
                }
                else if (cedula.getText().toString().isEmpty()) {
                    showMensaje("por favor ingrese su cedula");
                }
                else if (correo.getText().toString().isEmpty()){
                    showMensaje("por favor ingrese su correo");
                }
                else if (contrasenia.getText().toString().isEmpty()){
                    showMensaje("por favor ingrese su contrasenia");
                }
                else
                {
                    guardaPersona();
                }

                break;
            case R.id.btnRegresar2:
                Intent intent = new Intent(this,Ingreso.class);
                startActivity(intent);
                break;
            default:
                break;

        }



    }

    private void guardaPersona() {
        clienteRest = new ClienteRest(this);

        try {
            String url = "http://192.168.1.15:8080/MyEvents/rs/usuarios/crear-usuarios";

            Persona p = new Persona();

            p.setNombre(((EditText)findViewById(R.id.txtNombre)).getText().toString());
            p.setApellido(((EditText)findViewById(R.id.txtApellido)).getText().toString());
            p.setCedula(((EditText)findViewById(R.id.txtCedula)).getText().toString());
            p.setCorreo(((EditText)findViewById(R.id.txtCorreo)).getText().toString());
            p.setContrasenia(((EditText)findViewById(R.id.txtPassword)).getText().toString());

            clienteRest.doPost(url,p,WS_GUARDAR,true);
        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_GUARDAR){
            if(!clienteRest.isCancelled()){
                Respuesta respuesta = clienteRest.getResult(Respuesta.class);

                showMensaje("Guardado " + respuesta.getMensaje());
            }
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

}//fin clase RegistroActivity

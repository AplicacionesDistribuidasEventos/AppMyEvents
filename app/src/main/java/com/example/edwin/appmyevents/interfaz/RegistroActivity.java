package com.example.edwin.appmyevents.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import android.util.Patterns;
import android.support.v7.widget.Toolbar;
import java.util.regex.Pattern;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Login;
import com.example.edwin.appmyevents.interfaz.Modelo.Persona;
import com.example.edwin.appmyevents.interfaz.Modelo.Respuesta;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;



public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_GUARDAR = 1;
    private Persona persona = new Persona();
    private CheckBox opcionMostrar;

    EditText nombre;
    EditText apellido;
    EditText cedula;
    EditText correo;
    EditText contrasenia;
    //EditText contrasenia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnRegres = (Button) findViewById(R.id.btnRegresar2);
        btnRegres.setOnClickListener(this);
        opcionMostrar = (CheckBox)findViewById(R.id.opcion_mostrar);
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
                    Intent intent1 = new Intent(this,LoginActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.btnRegresar2:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }


    }




    public void mostrarContraseña(View v){
        // Salvar cursor
        int cursor = contrasenia.getSelectionEnd();

        if(opcionMostrar.isChecked()){
            contrasenia.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            contrasenia.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        // Restaurar cursor
        contrasenia.setSelection(cursor);
    }


        private void guardaPersona() {
        clienteRest = new ClienteRest(this);

        try {
            String url = "http://"+LoginActivity.dir_ip+":8080/MyEvents/rs/usuarios/crear-usuarios";

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
                //showMensaje("Guardado " + respuesta.getMensaje());
                showMensaje("Usurio Creado Exitosamente ");

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

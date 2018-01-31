package com.example.edwin.appmyevents.interfaz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Login;
import com.example.edwin.appmyevents.interfaz.Modelo.Persona;
import com.example.edwin.appmyevents.interfaz.Utilidades.Actualizar;
import com.example.edwin.appmyevents.interfaz.Utilidades.ClienteRest;
import com.example.edwin.appmyevents.interfaz.Utilidades.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

import static com.example.edwin.appmyevents.interfaz.MainActivity.codigo;

public class EditUsuario extends AppCompatActivity implements OnTaskCompleted, View.OnClickListener {

    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;

    EditText txtnombreEdit;
    EditText txtApellidoEdit;
    EditText txtCorreoEdit;
    Persona p = new Persona();
    String nombreN;
    String apellidoN;
    String correoN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_usuario);

        Button btnEditaUsuario = (Button)findViewById(R.id.btnEditaUsuario);
        btnEditaUsuario.setOnClickListener(this);
        Button btnRegres = (Button) findViewById(R.id.btnCancelar);
        btnRegres.setOnClickListener(this);

        txtnombreEdit = (EditText) findViewById(R.id.txtNombreEdit);

        txtApellidoEdit = (EditText) findViewById(R.id.txtApellidoEdit);

        txtCorreoEdit = (EditText) findViewById(R.id.txtCorreoEdit);

        editarUsuario();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditaUsuario:
                Actualizar ac = new Actualizar();
                setearValores();
                ac.actualizarUsuario(nombreN, apellidoN, correoN);

                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                CharSequence text = "Actualizado Exitosamente !!";
                int duracion = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context,text,duracion);
                toast.show();

                break;
            case R.id.btnCancelar:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
    public void setearValores(){
        nombreN = txtnombreEdit.getText().toString();
        apellidoN = txtApellidoEdit.getText().toString();
        correoN = txtCorreoEdit.getText().toString();
    }

    private void editarUsuario() {
        clienteRest = new ClienteRest(this);
        try {
            String url = "http://"+LoginActivity.dir_ip+"/MyEvents/rs/usuarios/informacion-usuario?id="+LoginActivity.cod_per;
            clienteRest.doGet(url,null,WS_CONSULTA,true);
        }catch (Exception e){
            e.printStackTrace();
            showMensaje("Error Logeo");
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if (idSolicitud == WS_CONSULTA) {
            if (!clienteRest.isCancelled()) {
                p = clienteRest.getResult(Persona.class);
                System.out.println("PERSONAAAAAAAAAAAA: "+p);
                String n = p.getNombre().toString();
                txtnombreEdit.setText(n);
                txtApellidoEdit.setText(p.getApellido());
                txtCorreoEdit.setText(p.getCorreo());
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
}

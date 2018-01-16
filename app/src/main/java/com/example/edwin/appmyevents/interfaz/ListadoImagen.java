package com.example.edwin.appmyevents.interfaz;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.edwin.appmyevents.R;
import com.squareup.picasso.Picasso;

public class ListadoImagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_imagen);

        ImageView imageView = (ImageView) findViewById(R.id.listado_imagen);
        Picasso.with(this)
                .load("http://192.168.0.102/MyEvents/imagenes/fondo.jpg")
                .error(R.mipmap.logo)
                .fit()
                .centerInside()
                .into(imageView);

    }

}

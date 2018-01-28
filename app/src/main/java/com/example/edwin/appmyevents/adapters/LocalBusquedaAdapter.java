package com.example.edwin.appmyevents.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Controlador.ReservaLocal;
import com.example.edwin.appmyevents.interfaz.ListarLocalesBusquedaActivity;
import com.example.edwin.appmyevents.interfaz.LoginActivity;
import com.example.edwin.appmyevents.interfaz.Modelo.Local;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class LocalBusquedaAdapter extends BaseAdapter {


    public class ViewHolder {
        TextView tvnombre, tvDescripcion, tvCosto, tvcapacidad, tvcomentario;
        ImageView tvfoto;
        Button btnreserva, llamar;
        EditText ecomentario;
        ListView listView;
    }

    public List<Local> listaLocal =new ArrayList<Local>();

    public Context context;

    public LocalBusquedaAdapter(List<Local> listaLocal, Context context) {
        this.listaLocal = listaLocal;
        this.context = context;
        System.out.println("LL"+listaLocal.get(0).getDescripcion());
        System.out.println("Construsctorrrrrrr");
    }

    @Override
    public int getCount() {
        return listaLocal.size();
    }

    @Override
    public Object getItem(int position) {
        return listaLocal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;
        System.out.println("Noooooooooooooooooooooooooooooo");
        if (rowView == null) {
            System.out.println("Siiiiiiiiiiiiiiiiiiiii");
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_adapter_local_busqueda, null);
            System.out.println("Siiiiiiiiiiiiiiiiiiiii2222222222222222222222");
            viewHolder = new ViewHolder();
            viewHolder.tvnombre = rowView.findViewById(R.id.nombre);
            viewHolder.tvDescripcion =rowView.findViewById(R.id.descripcion);
            viewHolder.tvcapacidad = rowView.findViewById(R.id.capacidad);
            viewHolder.tvCosto = rowView.findViewById(R.id.costo);
            viewHolder.tvfoto = rowView.findViewById(R.id.fotolocal);
            viewHolder.btnreserva = rowView.findViewById(R.id.reserva);
            viewHolder.llamar = rowView.findViewById(R.id.llamada);
            //viewHolder.ecomentario = rowView.findViewById(R.id.comentario);
            //viewHolder.tvcomentario = rowView.findViewById(R.id.comenariovista);
            //viewHolder.listView = rowView.findViewById(R.id.listaComentarios);
            System.out.println("Siiiiiiiiiiiiiiiiiiiii33333333333333333333333333");
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Local local = listaLocal.get(position);
        System.out.println("Siiiiiiiiiiiiiiiiiiiii4444444444444444444444");
        viewHolder.tvnombre.setText(local.getNombre());
        viewHolder.tvDescripcion.setText(local.getDescripcion()+"");
        viewHolder.tvcapacidad.setText(local.getCapacidad());
        viewHolder.tvCosto.setText(local.getCosto()+ " USD ");

        System.out.println("http://172.16.214.227/MyEvents/"+local.getFotoPerfil());
        Picasso.with(context).load("http://172.16.214.227/MyEvents/"+local.getFotoPerfil()).fit().into(viewHolder.tvfoto);


//        viewHolder.btnreserva.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Local local1 = (Local) getItem(position);
//                Toast.makeText(context,"Reserva Satisfactoria..!!!:"+local1.getCodigo(),Toast.LENGTH_SHORT).show();
//                String fecha = ListarLocalesBusquedaActivity.fec;
//                ReservaLocal res = new ReservaLocal(local1.getCodigo(), fecha);
//            }
//        });
//
//        viewHolder.llamar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Local l = (Local) getItem(position);
//                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+l.getTelefono()));
//                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
//                    return;
//                    context.startActivity(i);
//
//            }
//        });

        //viewHolder.ecomentario

        return rowView;
    }


}







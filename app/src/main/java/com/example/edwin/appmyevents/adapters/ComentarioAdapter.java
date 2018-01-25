package com.example.edwin.appmyevents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Comentario;

import java.util.ArrayList;
import java.util.List;


public class ComentarioAdapter extends BaseAdapter {


    public class ViewHolder {
        TextView tvnombres, tvFecha, tvComentario;
    }

    public List<Comentario> listaLocal=new ArrayList<Comentario>();

    public Context context;

    public ComentarioAdapter(List<Comentario> listaLocal, Context context) {
        this.listaLocal = listaLocal;
        this.context = context;
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
        ComentarioAdapter.ViewHolder viewHolder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_comentario_local, null);
            viewHolder = new ComentarioAdapter.ViewHolder();
            viewHolder.tvnombres = rowView.findViewById(R.id.nombres);
            viewHolder.tvFecha =rowView.findViewById(R.id.fecha);
            viewHolder.tvComentario = rowView.findViewById(R.id.comentario);
        } else {
            viewHolder = (ComentarioAdapter.ViewHolder) convertView.getTag();
        }

        Comentario local = listaLocal.get(position);
        viewHolder.tvnombres.setText(local.getNombre_p()+" " + local.getApellido_p());
        viewHolder.tvFecha.setText(local.getFecha_c()+"");
        viewHolder.tvComentario.setText(local.getComentario()+"");

        return rowView;
    }

}
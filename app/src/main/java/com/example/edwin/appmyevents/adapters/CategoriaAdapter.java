package com.example.edwin.appmyevents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Modelo.Categoria;
import java.util.ArrayList;
import java.util.List;


public class CategoriaAdapter extends BaseAdapter {

    public class ViewHolder {
        TextView tvnombre, tvDescripcion;
    }

    public List<Categoria> listaCategoria=new ArrayList<Categoria>();

    public Context context;

    public CategoriaAdapter(List<Categoria> listaCategoria, Context context) {
        this.listaCategoria = listaCategoria;
        this.context = context;

    }

    @Override
    public int getCount() {
        return listaCategoria.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCategoria.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_adapter_categoria, null);
            viewHolder = new ViewHolder();
            viewHolder.tvnombre = rowView.findViewById(R.id.nombre);
            viewHolder.tvDescripcion =rowView.findViewById(R.id.descripcion);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Categoria categoria = listaCategoria.get(position);

        viewHolder.tvnombre.setText(categoria.getNombre());
        viewHolder.tvDescripcion.setText(categoria.getDescipcion()+"");

        return rowView;
    }


}
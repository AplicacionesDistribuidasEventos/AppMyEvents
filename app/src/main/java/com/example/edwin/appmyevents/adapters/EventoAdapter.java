package com.example.edwin.appmyevents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwin.appmyevents.R;
import com.example.edwin.appmyevents.interfaz.Controlador.Confirmar_Asistencia;
import com.example.edwin.appmyevents.interfaz.Modelo.Evento;
import java.util.ArrayList;
import java.util.List;


public class EventoAdapter extends BaseAdapter {

    public class ViewHolder {
        TextView tvnombre, tvDescripcion, tvCosto, tvFecha;
        Button asistir, noasistir;
    }

    public List<Evento> listaEvento=new ArrayList<Evento>();

    public Context context;

    public EventoAdapter(List<Evento> listaEvento, Context context) {
        this.listaEvento = listaEvento;
        this.context = context;

    }

    @Override
    public int getCount() {
        return listaEvento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEvento.get(position);
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
            rowView = inflater.inflate(R.layout.layout_adapter_evento, null);
            viewHolder = new ViewHolder();
            viewHolder.tvnombre = rowView.findViewById(R.id.nombre);
            viewHolder.tvDescripcion =rowView.findViewById(R.id.descripcion);
            viewHolder.tvFecha = rowView.findViewById(R.id.fechaEvento);
            viewHolder.tvCosto = rowView.findViewById(R.id.costo);
            viewHolder.asistir = rowView.findViewById(R.id.btnAsistire);
            viewHolder.noasistir = rowView.findViewById(R.id.btnNoAsistire);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Evento evento = listaEvento.get(position);

        viewHolder.tvnombre.setText(evento.getNombre());
        viewHolder.tvDescripcion.setText(evento.getDescripcion()+"");
        viewHolder.tvFecha.setText(evento.getFechaEvento());
        viewHolder.tvCosto.setText(evento.getCosto()+ " USD ");


        /**
         * ACCIONES PARA LOS BOTONES
         * */
/**ASISTIR*/
        viewHolder.asistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evento evento1 = (Evento) getItem(position);
//                Toast.makeText(getContext(), "POSICION VISUAL: "+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"ASISTIRE A ID:"+evento1.getCodigo(),Toast.LENGTH_SHORT).show();
                String estado = String.valueOf(true);
                Confirmar_Asistencia wsca = new Confirmar_Asistencia(evento1.getCodigo(), estado);
            }
        });
/**NO ASISTIR*/
       viewHolder.noasistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Evento currentEvento = (Evento) getItem(position);
                Toast.makeText(context,"NO ASISTIRE A ID:"+currentEvento.getCodigo(),Toast.LENGTH_SHORT).show();
                String estado = String.valueOf(false);
                Confirmar_Asistencia wsca = new Confirmar_Asistencia(currentEvento.getCodigo(), estado);

            }
        });

        return rowView;
    }


}







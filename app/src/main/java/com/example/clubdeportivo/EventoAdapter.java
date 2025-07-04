package com.example.clubdeportivo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubdeportivo.model.Evento;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    private List<Evento> eventos; // 👈 Corregido aquí

    private Context context;

    public EventoAdapter(List<Evento> eventos, Context context) {
        this.eventos = eventos;
        this.context = context;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        int imageRes = context.getResources().getIdentifier(
                evento.getImagenEvento(), "drawable", context.getPackageName());
        holder.imgEvento.setImageResource(imageRes);

        holder.txtNombre.setText(evento.getNombreEvento());
        holder.txtFechas.setText("Lanzamiento: " + evento.getFechaLanzamiento() + " | Realización: " + evento.getFechaRealizacion());
        holder.txtOrganizador.setText("Organizado por: " + evento.getNombreOrganizador());

        holder.btnVerMas.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventoDetallesActivity.class);
            intent.putExtra("evento_id", evento.getId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtFechas, txtOrganizador;
        Button btnVerMas;
        ImageView imgEvento;
        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEvento = itemView.findViewById(R.id.imgEvento);
            txtNombre = itemView.findViewById(R.id.txtNombreEvento);
            txtFechas = itemView.findViewById(R.id.txtFechas);
            txtOrganizador = itemView.findViewById(R.id.txtOrganizador);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}

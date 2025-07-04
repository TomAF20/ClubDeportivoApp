package com.example.clubdeportivo;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubdeportivo.model.Cancha;

import java.util.List;
public class CanchaAdapter extends RecyclerView.Adapter<CanchaAdapter.CanchaViewHolder> {

    private final List<Cancha> canchas;
    private final Context context;

    public CanchaAdapter(Context context, List<Cancha> canchas) {
        this.context = context;
        this.canchas = canchas;
    }

    @NonNull
    @Override
    public CanchaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cancha, parent, false);
        return new CanchaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CanchaViewHolder holder, int position) {
        Cancha cancha = canchas.get(position);
        holder.txtTitulo.setText("CANCHA " + cancha.getId());

        if ("Disponible".equalsIgnoreCase(cancha.getDisponibilidad())) {
            holder.txtDisponibilidad.setText("Turnos disponibles");
            holder.txtDisponibilidad.setTextColor(Color.parseColor("#00FF9D"));
        } else {
            holder.txtDisponibilidad.setText("No hay turnos disponibles");
            holder.txtDisponibilidad.setTextColor(Color.parseColor("#FF6B6B"));
        }
    }

    @Override
    public int getItemCount() {
        return canchas.size();
    }

    static class CanchaViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtDisponibilidad;

        public CanchaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloCancha);
            txtDisponibilidad = itemView.findViewById(R.id.txtDisponibilidadCancha);
        }
    }
}
package com.example.clubdeportivo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder> {

    private final List<Reporte> reportes;

    public ReporteAdapter(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @NonNull
    @Override
    public ReporteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ReporteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteViewHolder holder, int position) {
        Reporte r = reportes.get(position);
        holder.tipo.setText("Tipo: " + r.getTipo());
        holder.estado.setText("Estado: " + r.getEstado());
    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }

    static class ReporteViewHolder extends RecyclerView.ViewHolder {
        TextView tipo, estado;

        public ReporteViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo = itemView.findViewById(android.R.id.text1);
            estado = itemView.findViewById(android.R.id.text2);
        }
    }
}

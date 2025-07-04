package com.example.clubdeportivo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngresoAdapter extends RecyclerView.Adapter<IngresoAdapter.ViewHolder> {

    private ArrayList<Ingreso> ingresos;

    public IngresoAdapter(ArrayList<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public IngresoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingreso, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngresoAdapter.ViewHolder holder, int position) {
        Ingreso ingreso = ingresos.get(position);
        holder.textConcepto.setText(ingreso.getConcepto());
        holder.textFecha.setText("Fecha: " + ingreso.getFecha());
        holder.textImporte.setText("S/. " + String.format("%.2f", ingreso.getImporte()));
    }

    @Override
    public int getItemCount() {
        return ingresos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textConcepto, textFecha, textImporte;

        public ViewHolder(View itemView) {
            super(itemView);
            textConcepto = itemView.findViewById(R.id.text_concepto);
            textFecha = itemView.findViewById(R.id.text_fecha);
            textImporte = itemView.findViewById(R.id.text_importe);
        }
    }
}

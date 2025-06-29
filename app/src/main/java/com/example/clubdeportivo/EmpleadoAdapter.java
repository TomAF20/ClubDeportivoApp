package com.example.clubdeportivo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder> {

    private List<Empleado> empleados;

    public EmpleadoAdapter(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public EmpleadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new EmpleadoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmpleadoViewHolder holder, int position) {
        Empleado empleado = empleados.get(position);
        holder.bind(empleado);
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvApellidos, tvDni, tvCargo, tvCorreo, tvContrasena;
        View contenidoExpandible;

        public EmpleadoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            tvApellidos = itemView.findViewById(R.id.tv_apellidos);
            tvDni = itemView.findViewById(R.id.tv_dni);
            tvCargo = itemView.findViewById(R.id.tv_cargo);
            tvCorreo = itemView.findViewById(R.id.tv_correo);
            tvContrasena = itemView.findViewById(R.id.tv_contrasena);
            contenidoExpandible = itemView.findViewById(R.id.contenido_expandible);
        }

        public void bind(Empleado empleado) {
            tvNombre.setText(empleado.getNombre());
            tvApellidos.setText("Apellidos: " + empleado.getApellidos());
            tvDni.setText("DNI: " + empleado.getDni());
            tvCargo.setText("Cargo: " + empleado.getCargo());
            tvCorreo.setText("Correo: " + empleado.getCorreo());
            tvContrasena.setText("Contraseña: " + empleado.getContrasena());

            contenidoExpandible.setVisibility(empleado.isExpandido() ? View.VISIBLE : View.GONE);

            tvNombre.setOnClickListener(v -> {
                empleado.setExpandido(!empleado.isExpandido());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}

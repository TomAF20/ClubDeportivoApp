package com.example.clubdeportivo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios = new ArrayList<>();

    public void setUsuarios(List<Usuario> lista) {
        this.usuarios = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empleado, parent, false);
        return new UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.bind(usuario);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvRol, tvDni, tvCorreo, tvContrasena;
        View contenidoExpandible;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            tvRol = itemView.findViewById(R.id.tv_cargo);
            tvDni = itemView.findViewById(R.id.tv_dni);
            tvCorreo = itemView.findViewById(R.id.tv_correo);
            tvContrasena = itemView.findViewById(R.id.tv_contrasena);
            contenidoExpandible = itemView.findViewById(R.id.contenido_expandible);
        }

        public void bind(Usuario usuario) {
            tvNombre.setText(usuario.getNombreCompleto());
            tvRol.setText("Rol: " + usuario.getRol());
            tvDni.setText("DNI: " + usuario.getDni());
            tvCorreo.setText("Correo: " + usuario.getCorreo());
            tvContrasena.setText("Contraseña: " + usuario.getContrasena());

            contenidoExpandible.setVisibility(usuario.isExpandido() ? View.VISIBLE : View.GONE);

            tvNombre.setOnClickListener(v -> {
                usuario.setExpandido(!usuario.isExpandido());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
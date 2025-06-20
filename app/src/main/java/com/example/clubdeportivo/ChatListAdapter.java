package com.example.clubdeportivo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    private List<ChatItem> listaOriginal;
    private List<ChatItem> listaFiltrada;
    private final OnChatClickListener listener;

    public interface OnChatClickListener {
        void onChatClick(ChatItem chatItem);
    }

    public ChatListAdapter(List<ChatItem> listaChats, OnChatClickListener listener) {
        this.listaOriginal = new ArrayList<>(listaChats);
        this.listaFiltrada = new ArrayList<>(listaChats);
        this.listener = listener;
    }

    public void filtrar(String texto) {
        listaFiltrada.clear();
        if (texto.isEmpty()) {
            listaFiltrada.addAll(listaOriginal);
        } else {
            for (ChatItem item : listaOriginal) {
                if (item.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem item = listaFiltrada.get(position);
        holder.txtNombre.setText(item.getNombre());
        holder.txtUltimoMensaje.setText(item.getUltimoMensaje());
        holder.itemView.setOnClickListener(v -> listener.onChatClick(item));
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtUltimoMensaje;
        ChatViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtUltimoMensaje = itemView.findViewById(R.id.txtUltimoMensaje);
        }
    }
}
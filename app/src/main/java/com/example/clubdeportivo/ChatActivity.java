package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etBuscar;
    private ChatListAdapter adapter;
    private List<ChatItem> listaChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerChats);
        etBuscar = findViewById(R.id.search_chat); // CORREGIDO

        listaChats = new ArrayList<>();
        listaChats.add(new ChatItem("Carlos", "Hola, ¿cómo estás?"));
        listaChats.add(new ChatItem("María", "Nos vemos mañana"));
        listaChats.add(new ChatItem("IA Asistente", "Haz clic para chatear"));

        adapter = new ChatListAdapter(listaChats, chatItem -> {
            Intent intent = new Intent(ChatActivity.this, ConversacionActivity.class);
            intent.putExtra("nombre", chatItem.getNombre());
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_chat);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_canchas) {
                startActivity(new Intent(this, CanchasActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_eventos) {
                startActivity(new Intent(this, EventosActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(this, ReporteActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_chat) {
                return true;
            }
            return false;
        });
    }
}

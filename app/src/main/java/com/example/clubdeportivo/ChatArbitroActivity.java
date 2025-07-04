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

public class ChatArbitroActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etBuscar;
<<<<<<< HEAD
    private ChatListAdapter adapter;
    private List<ChatItem> listaChats;
=======
    private ChatArbitroListAdapter adapter;
    private List<ChatItemArbitro> listaChats;
>>>>>>> origin/main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_arbitro);
        Toolbar toolbar = findViewById(R.id.toolbar_partidos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chat");
        }


<<<<<<< HEAD
        recyclerView = findViewById(R.id.recyclerChats);
        etBuscar = findViewById(R.id.search_chat); // CORREGIDO

        listaChats = new ArrayList<>();
        listaChats.add(new ChatItem("Carlos", "Hola, ¿cómo estás?"));
        listaChats.add(new ChatItem("María", "Nos vemos mañana"));
        listaChats.add(new ChatItem("IA Asistente", "Haz clic para chatear"));

        adapter = new ChatListAdapter(listaChats, chatItem -> {
            Intent intent = new Intent(ChatArbitroActivity.this, ConversacionArbitroActivity.class);
            intent.putExtra("nombre", chatItem.getNombre());
=======
        recyclerView = findViewById(R.id.recyclerChatsArbitro);
        etBuscar = findViewById(R.id.search_chat_arbitro); // CORREGIDO

        listaChats = new ArrayList<>();
        listaChats.add(new ChatItemArbitro("Carlos", "Hola, ¿cómo estás?"));
        listaChats.add(new ChatItemArbitro("María", "Nos vemos mañana"));
        listaChats.add(new ChatItemArbitro("IA Asistente", "Haz clic para chatear"));

        adapter = new ChatArbitroListAdapter(listaChats, chatItemArbitro -> {
            Intent intent = new Intent(ChatArbitroActivity.this, ConversacionArbitroActivity.class);
            intent.putExtra("nombre", chatItemArbitro.getNombre());
>>>>>>> origin/main
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
<<<<<<< HEAD
        bottomNavigationView.setSelectedItemId(R.id.nav_partidos);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_chat_arbitro) {
                startActivity(new Intent(this, ChatArbitroActivity.class));
=======
        bottomNavigationView.setSelectedItemId(R.id.nav_chat_arbitro);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_partidos) {
                startActivity(new Intent(this, ArbitroActivity.class));
>>>>>>> origin/main
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_historial_arbitro) {
                startActivity(new Intent(this, HistorialActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_reportar_arbitro) {
                startActivity(new Intent(this, ReportarProblemaActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_perfil_arbitro) {
                startActivity(new Intent(this, PerfilArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
<<<<<<< HEAD
            return id == R.id.nav_partidos;
=======
            return id == R.id.nav_chat_arbitro;
>>>>>>> origin/main
        });
    }
}

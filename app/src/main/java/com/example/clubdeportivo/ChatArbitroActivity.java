package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChatArbitroActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etBuscar;
    private ChatArbitroListAdapter adapter;
    private List<ChatItemArbitro> listaChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_arbitro);

        Toolbar toolbar = findViewById(R.id.toolbar_partidos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chat");
        }

        recyclerView = findViewById(R.id.recyclerChatsArbitro);
        etBuscar = findViewById(R.id.search_chat_arbitro);

        // Lista de ejemplo
        listaChats = new ArrayList<>();
        listaChats.add(new ChatItemArbitro("Carlos", "Hola, ¿cómo estás?"));
        listaChats.add(new ChatItemArbitro("María", "Nos vemos mañana"));
        listaChats.add(new ChatItemArbitro("IA Asistente", "Haz clic para chatear"));

        adapter = new ChatArbitroListAdapter(listaChats, chatItemArbitro -> {
            Intent intent = new Intent(ChatArbitroActivity.this, ConversacionArbitroActivity.class);
            intent.putExtra("nombre", chatItemArbitro.getNombre());
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
        bottomNavigationView.setSelectedItemId(R.id.nav_chat_arbitro);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_partidos) {
                startActivity(new Intent(this, ArbitroActivity.class));
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
            return id == R.id.nav_chat_arbitro;
        });
    }
}

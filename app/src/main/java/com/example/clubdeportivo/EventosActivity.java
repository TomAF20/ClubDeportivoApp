package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Evento;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> listaEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_eventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventoAdapter(listaEventos, this);
        recyclerView.setAdapter(adapter);

        obtenerEventosDesdeBackend();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_eventos);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_canchas) {
                startActivity(new Intent(this, CanchasActivity.class));
                finish(); return true;
            } else if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
                finish(); return true;
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ChatActivity.class));
                finish(); return true;
            } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(this, ReporteActivity.class));
                finish(); return true;
            }
            return id == R.id.nav_eventos;
        });
    }

    private void obtenerEventosDesdeBackend() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<List<Evento>> call = apiService.getEventos();

        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful()) {
                    listaEventos.clear();
                    listaEventos.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(EventosActivity.this, "Error al obtener eventos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

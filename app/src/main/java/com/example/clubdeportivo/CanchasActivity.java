package com.example.clubdeportivo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Cancha;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CanchasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CanchaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canchas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_canchas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        obtenerCanchas();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_canchas);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_eventos) {
                startActivity(new Intent(this, EventosActivity.class));
            } else if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ChatActivity.class));
            } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(this, ReporteActivity.class));
            }
            overridePendingTransition(0, 0);
            finish();
            return true;
        });
    }

    private void obtenerCanchas() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getCanchas().enqueue(new Callback<List<Cancha>>() {
            @Override
            public void onResponse(Call<List<Cancha>> call, Response<List<Cancha>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new CanchaAdapter(CanchasActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cancha>> call, Throwable t) {
                Log.e("Retrofit", "Error al obtener canchas", t);
            }
        });
    }

    private static class CanchaAdapter extends RecyclerView.Adapter<CanchaAdapter.CanchaViewHolder> {
        private final List<Cancha> canchas;
        private final AppCompatActivity activity;

        public CanchaAdapter(AppCompatActivity activity, List<Cancha> canchas) {
            this.activity = activity;
            this.canchas = canchas;
        }

        @Override
        public CanchaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancha, parent, false);
            return new CanchaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CanchaViewHolder holder, int position) {
            Cancha cancha = canchas.get(position);
            holder.txtTitulo.setText("CANCHA " + cancha.getId());

            if ("Disponible".equalsIgnoreCase(cancha.getDisponibilidad())) {
                holder.txtDisponibilidad.setText("Turnos disponibles");
                holder.txtDisponibilidad.setTextColor(Color.parseColor("#00FF9D"));
            } else {
                holder.txtDisponibilidad.setText("No hay turnos disponibles");
                holder.txtDisponibilidad.setTextColor(Color.parseColor("#FF6B6B"));
            }

            holder.btnVerMas.setOnClickListener(v -> {
                Intent intent = new Intent(activity, CanchaDetalleActivity.class);
                intent.putExtra("canchaId", cancha.getId());
                activity.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return canchas.size();
        }

        static class CanchaViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitulo, txtDisponibilidad;
            Button btnVerMas;

            public CanchaViewHolder(View itemView) {
                super(itemView);
                txtTitulo = itemView.findViewById(R.id.txtTituloCancha);
                txtDisponibilidad = itemView.findViewById(R.id.txtDisponibilidadCancha);
                btnVerMas = itemView.findViewById(R.id.btnVerMas);
            }
        }
    }
}

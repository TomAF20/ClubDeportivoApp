package com.example.clubdeportivo;

import android.os.Bundle;
import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class ReporteActivity extends AppCompatActivity {

    private Spinner spinnerTipo;
    private EditText etDetalle;
    private Button btnEnviar;
    private RecyclerView recyclerView;
    private ReporteAdapter adapter;
    private List<Reporte> listaReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        etDetalle = findViewById(R.id.etDetalle);
        btnEnviar = findViewById(R.id.btnEnviar);
        recyclerView = findViewById(R.id.recyclerReportes);

        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"Error en cancha", "Error de reserva", "Problema técnico", "Otro"});
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(tipoAdapter);

        listaReportes = new ArrayList<>();
        adapter = new ReporteAdapter(listaReportes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnEnviar.setOnClickListener(v -> {
            String tipo = spinnerTipo.getSelectedItem().toString();
            String detalle = etDetalle.getText().toString().trim();
            if (!detalle.isEmpty()) {
                listaReportes.add(new Reporte(tipo, detalle, "Pendiente"));
                adapter.notifyItemInserted(listaReportes.size() - 1);
                etDetalle.setText("");
                Toast.makeText(this, "Reporte enviado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ingresa un detalle", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_feedback);
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
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ChatActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_feedback) {
                return true;
            }
            return false;
        });
    }
}

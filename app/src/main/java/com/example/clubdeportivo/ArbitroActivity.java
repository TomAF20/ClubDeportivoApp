package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArbitroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arbitro);

        Toolbar toolbar = findViewById(R.id.toolbar_partidos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Árbitro");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_partidos);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_chat_arbitro) {
                startActivity(new Intent(this, ChatArbitroActivity.class));
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
            return id == R.id.nav_partidos;
        });

<<<<<<< HEAD
=======
        Button verMasBtn = findViewById(R.id.btn_ver_mas_partido1);
        verMasBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ArbitroActivity.this, DetallesPartidoActivity.class);
            startActivity(intent);
        });
>>>>>>> origin/main

    }
}

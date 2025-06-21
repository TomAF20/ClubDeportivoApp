package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ArbitroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arbitro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Árbitro");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        Button btnPartidos = findViewById(R.id.btnPartidos);
        Button btnChat = findViewById(R.id.btnChat);
        Button btnHistorial = findViewById(R.id.btnHistorial);
        Button btnReportar = findViewById(R.id.btnReportar);
        Button btnPerfil = findViewById(R.id.btnPerfil);

        btnPartidos.setOnClickListener(v -> startActivity(new Intent(this, PartidosAsignadosActivity.class)));
        btnChat.setOnClickListener(v -> startActivity(new Intent(this, ChatArbitroActivity.class)));
        btnHistorial.setOnClickListener(v -> startActivity(new Intent(this, HistorialActivity.class)));
        btnReportar.setOnClickListener(v -> startActivity(new Intent(this, ReportarProblemaActivity.class)));
        btnPerfil.setOnClickListener(v -> startActivity(new Intent(this, PerfilArbitroActivity.class)));
    }
}

package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class CanchaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancha_detalle);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        btnBack.setOnClickListener(v -> finish());

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(CanchaDetalleActivity.this, JugadoresCanchaActivity.class);
            startActivity(intent);
        });
    }
}
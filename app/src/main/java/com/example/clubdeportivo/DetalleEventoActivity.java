package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleEventoActivity extends AppCompatActivity {

    private Button btnInscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_detalle);

        btnInscribir = findViewById(R.id.btn_inscribir);

        btnInscribir.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleEventoActivity.this, JugadoresEventoActivity.class);
            startActivity(intent);
        });
    }
}

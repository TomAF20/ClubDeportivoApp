package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventoDetallesActivity extends AppCompatActivity {

    private Button btnInscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_detalle);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_evento_detalle);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        btnInscribir = findViewById(R.id.btn_inscribir);
        btnInscribir.setOnClickListener(v -> {
            Intent intent = new Intent(EventoDetallesActivity.this, JugadoresEventoActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {

        return true;
    }

}
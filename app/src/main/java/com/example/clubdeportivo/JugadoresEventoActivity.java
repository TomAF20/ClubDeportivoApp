package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class JugadoresEventoActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_evento);

        btnBack = findViewById(R.id.btnBack);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Vuelve atrás
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JugadoresEventoActivity.this, EquiposEventoActivity.class);
                startActivity(intent);
            }
        });
    }
}
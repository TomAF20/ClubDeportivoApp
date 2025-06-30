package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilMedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_medico);

        ImageButton btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilMedActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        Button btnRegistrar = findViewById(R.id.btn_registros);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilMedActivity.this, RegistroMedicoActivity.class);
                startActivity(intent);
            }
        });

    }
}

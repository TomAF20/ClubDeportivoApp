package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResumenRegistroActivity extends AppCompatActivity {

    private TextView tvNombre, tvEdad, tvSintomas, tvDiagnostico, tvTratamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumen_registro);

        tvNombre = findViewById(R.id.tvNombre);
        tvEdad = findViewById(R.id.tvEdad);
        tvSintomas = findViewById(R.id.tvSintomas);
        tvDiagnostico = findViewById(R.id.tvDiagnostico);
        tvTratamiento = findViewById(R.id.tvTratamiento);

        // Obtener datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvNombre.setText("Nombre: " + extras.getString("nombre", ""));
            tvEdad.setText("Edad: " + extras.getString("edad", ""));
            tvSintomas.setText("Síntomas: " + extras.getString("sintomas", ""));
            tvDiagnostico.setText("Diagnóstico: " + extras.getString("diagnostico", ""));
            tvTratamiento.setText("Tratamiento: " + extras.getString("tratamiento", ""));
        }

        ImageButton btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumenRegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btnEnviar = findViewById(R.id.btn_enviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumenRegistroActivity.this, PerfilMedActivity.class);
                startActivity(intent);
            }
        });

        Button btnRegistrar = findViewById(R.id.btn_registros);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ResumenRegistroActivity.this, RegistroMedicoActivity.class);
                startActivity(intent);
            }
        });
    }
}

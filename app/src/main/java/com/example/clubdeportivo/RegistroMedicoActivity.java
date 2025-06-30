package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroMedicoActivity extends AppCompatActivity {

    private EditText etNombre, etEdad, etSintomas, etDiagnostico, etTratamiento;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_medico);

        ImageButton btnSalir = findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroMedicoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btnPerfil = findViewById(R.id.btn_perfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroMedicoActivity.this, PerfilMedActivity.class);
                startActivity(intent);
            }
        });


        etNombre = findViewById(R.id.etNombrePaciente);
        etEdad = findViewById(R.id.etEdad);
        etSintomas = findViewById(R.id.etSintomas);
        etDiagnostico = findViewById(R.id.etDiagnostico);
        etTratamiento = findViewById(R.id.etTratamiento);
        btnGuardar = findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRegistro();
            }
        });
    }

    private void guardarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String edad = etEdad.getText().toString().trim();
        String sintomas = etSintomas.getText().toString().trim();
        String diagnostico = etDiagnostico.getText().toString().trim();
        String tratamiento = etTratamiento.getText().toString().trim();

        if (nombre.isEmpty() || edad.isEmpty() || sintomas.isEmpty()) {
            Toast.makeText(this, "Por favor completa los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(RegistroMedicoActivity.this, ResumenRegistroActivity.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("edad", edad);
        intent.putExtra("sintomas", sintomas);
        intent.putExtra("diagnostico", diagnostico);
        intent.putExtra("tratamiento", tratamiento);
        startActivity(intent);
    }


}

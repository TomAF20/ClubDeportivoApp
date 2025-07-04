package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroMedicoActivity extends AppCompatActivity {

    private EditText etNombre, etEdad, etSintomas, etDiagnostico, etTratamiento;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_medico);

        // Botones de navegación
        ImageButton btnSalir = findViewById(R.id.btn_salir);
        Button btnPerfil = findViewById(R.id.btn_perfil);

        btnSalir.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroMedicoActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroMedicoActivity.this, PerfilMedActivity.class);
            startActivity(intent);
        });

        // Campos del formulario
        etNombre = findViewById(R.id.etNombrePaciente);
        etEdad = findViewById(R.id.etEdad);
        etSintomas = findViewById(R.id.etSintomas);
        etDiagnostico = findViewById(R.id.etDiagnostico);
        etTratamiento = findViewById(R.id.etTratamiento);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Botón guardar
        btnGuardar.setOnClickListener(v -> guardarRegistro());
    }

    private void guardarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();
        String sintomas = etSintomas.getText().toString().trim();
        String diagnostico = etDiagnostico.getText().toString().trim();
        String tratamiento = etTratamiento.getText().toString().trim();

        if (nombre.isEmpty() || edadStr.isEmpty() || sintomas.isEmpty()) {
            Toast.makeText(this, "Por favor completa los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            RegistroMedico dto = new RegistroMedico(nombre, edad, sintomas, diagnostico, tratamiento);

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<ResponseBody> call = apiService.guardarRegistroMedico(dto);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegistroMedicoActivity.this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroMedicoActivity.this, ResumenRegistroActivity.class);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("edad", edadStr);
                        intent.putExtra("sintomas", sintomas);
                        intent.putExtra("diagnostico", diagnostico);
                        intent.putExtra("tratamiento", tratamiento);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegistroMedicoActivity.this, "Error al guardar: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(RegistroMedicoActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Edad inválida", Toast.LENGTH_SHORT).show();
        }
    }
}
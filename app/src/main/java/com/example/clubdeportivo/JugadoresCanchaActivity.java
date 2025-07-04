package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugadoresCanchaActivity extends AppCompatActivity {

    Spinner spinnerCantidad;
    LinearLayout contenedorJugadores;
    Button btnSiguiente;
    ImageButton btnBack;
    String horarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_cancha);

        horarioSeleccionado = getIntent().getStringExtra("horarioSeleccionado");
        spinnerCantidad = findViewById(R.id.spinnerCantidad);
        contenedorJugadores = findViewById(R.id.contenedorJugadores);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnBack = findViewById(R.id.btnBack);

        String[] opciones = new String[22];
        for (int i = 0; i < 22; i++) {
            opciones[i] = (i + 1) + " jugadores";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        spinnerCantidad.setAdapter(adapter);

        spinnerCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generarCamposJugadores(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnSiguiente.setOnClickListener(v -> validarDnisYContinuar());

        btnBack.setOnClickListener(v -> finish());
    }

    private void generarCamposJugadores(int cantidad) {
        contenedorJugadores.removeAllViews();
        for (int i = 1; i <= cantidad; i++) {
            EditText campoDni = new EditText(this);
            campoDni.setHint("DNI del Jugador " + i);
            campoDni.setInputType(InputType.TYPE_CLASS_NUMBER);
            campoDni.setBackgroundResource(R.drawable.campo_texto_blanco);
            campoDni.setPadding(30, 30, 30, 30);
            campoDni.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            campoDni.setHintTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 20);
            campoDni.setLayoutParams(params);
            contenedorJugadores.addView(campoDni);
        }
    }

    private void validarDnisYContinuar() {
        int total = contenedorJugadores.getChildCount();
        if (total == 0) return;

        validarDniRecursivamente(0, total);
    }

    private void validarDniRecursivamente(int index, int total) {
        if (index >= total) {
            startActivity(new Intent(this, ConfirmarReservaCanchaActivity.class));
            return;
        }

        EditText campoDni = (EditText) contenedorJugadores.getChildAt(index);
        String dni = campoDni.getText().toString().trim();

        if (dni.isEmpty()) {
            campoDni.setError("DNI requerido");
            return;
        }

        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        Call<Boolean> call = api.validarDniSocio(dni);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && Boolean.TRUE.equals(response.body())) {
                    validarDniRecursivamente(index + 1, total);
                } else {
                    campoDni.setError("No es socio registrado");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(JugadoresCanchaActivity.this, "Error de red al validar DNI", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

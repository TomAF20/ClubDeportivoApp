package com.example.clubdeportivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.EquipoEventoDTO;
import com.example.clubdeportivo.model.JugadorDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugadoresEventoActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnSiguiente;
    private EditText txtNombreEquipo;
    private List<EditText> dniJugadores = new ArrayList<>();

    private ApiService apiService;
    private long eventoId;
    private long socioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_evento);

        btnBack = findViewById(R.id.btnBack);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtNombreEquipo = findViewById(R.id.nombre_equipo);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        eventoId = getIntent().getLongExtra("evento_id", -1L);

        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        socioId = prefs.getLong("id", -1L);

        LinearLayout contenedorJugadores = findViewById(R.id.contenedorJugadores);
        for (int i = 1; i <= 11; i++) {
            EditText dniInput = contenedorJugadores.findViewWithTag("dni" + i);
            if (dniInput != null) {
                dniJugadores.add(dniInput);
            }
        }

        btnBack.setOnClickListener(v -> finish());

        btnSiguiente.setOnClickListener(v -> registrarEquipo());
    }

    private void registrarEquipo() {
        String nombreEquipo = txtNombreEquipo.getText().toString().trim();

        if (nombreEquipo.isEmpty()) {
            Toast.makeText(this, "Ingresa el nombre del equipo", Toast.LENGTH_SHORT).show();
            return;
        }

        List<JugadorDTO> jugadores = new ArrayList<>();
        for (EditText dniInput : dniJugadores) {
            String dni = dniInput.getText().toString().trim();
            if (!dni.isEmpty()) {
                jugadores.add(new JugadorDTO(dni));
            }
        }

        if (jugadores.isEmpty()) {
            Toast.makeText(this, "Debe ingresar al menos un jugador con DNI", Toast.LENGTH_SHORT).show();
            return;
        }

        validarTodosLosDnis(jugadores, nombreEquipo);
    }

    private void validarTodosLosDnis(List<JugadorDTO> jugadores, String nombreEquipo) {
        final int[] validados = {0};
        final boolean[] error = {false};

        for (JugadorDTO jugador : jugadores) {
            apiService.validarDniSocio(jugador.getDni()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    validados[0]++;
                    if (!Boolean.TRUE.equals(response.body())) {
                        error[0] = true;
                        Toast.makeText(JugadoresEventoActivity.this,
                                "El DNI " + jugador.getDni() + " no está registrado como socio",
                                Toast.LENGTH_SHORT).show();
                    }

                    if (validados[0] == jugadores.size() && !error[0]) {
                        enviarEquipo(nombreEquipo, jugadores);
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(JugadoresEventoActivity.this,
                            "Error al validar DNI: " + jugador.getDni(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void enviarEquipo(String nombreEquipo, List<JugadorDTO> jugadores) {
        EquipoEventoDTO equipoDTO = new EquipoEventoDTO();
        equipoDTO.setNombreEquipo(nombreEquipo);
        equipoDTO.setIdEvento(eventoId);
        equipoDTO.setIdSocio(socioId);
        equipoDTO.setJugadores(jugadores);

        apiService.registrarEquipo(equipoDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(JugadoresEventoActivity.this,
                            "Equipo registrado correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(JugadoresEventoActivity.this, EquiposEventoActivity.class);
                    intent.putExtra("evento_id", eventoId); // Por si lo usas allá
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(JugadoresEventoActivity.this,
                            "Error al registrar el equipo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(JugadoresEventoActivity.this,
                        "Fallo en el registro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

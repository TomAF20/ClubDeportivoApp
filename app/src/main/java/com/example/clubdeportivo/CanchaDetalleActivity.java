package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Cancha;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CanchaDetalleActivity extends AppCompatActivity {

    private TextView tvNombreCancha, tvMedidas, tvArbitro;
    private GridLayout gridHorarios;
    private ImageView imgCancha;
    private Button btnSiguiente;
    private ImageButton btnBack;

    private Long canchaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancha_detalle);

        tvNombreCancha = findViewById(R.id.tvNombreCancha);
        tvMedidas = findViewById(R.id.tvMedidas);
        tvArbitro = findViewById(R.id.tvArbitro);
        gridHorarios = findViewById(R.id.gridHorarios);
        imgCancha = findViewById(R.id.imgCancha);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnBack = findViewById(R.id.btnBack);

        canchaId = getIntent().getLongExtra("canchaId", -1);

        if (canchaId != -1) {
            cargarDatosCancha(canchaId);
        } else {
            Toast.makeText(this, "ID de cancha no válido", Toast.LENGTH_SHORT).show();
        }

        btnBack.setOnClickListener(v -> finish());
        btnSiguiente.setOnClickListener(v -> {
            if (horarioSeleccionado == null) {
                Toast.makeText(this, "Selecciona un horario", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CanchaDetalleActivity.this, JugadoresCanchaActivity.class);
            intent.putExtra("canchaId", canchaId);
            intent.putExtra("horarioSeleccionado", horarioSeleccionado);
            startActivity(intent);
        });


    }

    private void cargarDatosCancha(Long id) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<Cancha> call = apiService.getCanchaById(id);
        call.enqueue(new Callback<Cancha>() {
            @Override
            public void onResponse(Call<Cancha> call, Response<Cancha> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarDatos(response.body());
                } else {
                    Toast.makeText(CanchaDetalleActivity.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cancha> call, Throwable t) {
                Toast.makeText(CanchaDetalleActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDatos(Cancha cancha) {
        tvNombreCancha.setText("CANCHA " + cancha.getId());
        tvMedidas.setText("• Grass sintético\n• Medida: " + cancha.getLargo() + "M x " + cancha.getAncho() + "M");
        tvArbitro.setText(cancha.getArbitroNombre() != null ? cancha.getArbitroNombre() : "No asignado");

        agregarHorarios(cancha.getHorariosDisponibles());
    }

    private String horarioSeleccionado = null;

    private void agregarHorarios(List<String> horarios) {
        gridHorarios.removeAllViews();
        if (horarios == null || horarios.isEmpty()) return;

        for (String h : horarios) {
            TextView horarioView = new TextView(this);
            horarioView.setText(h);
            horarioView.setPadding(12, 12, 12, 12);
            horarioView.setBackgroundResource(R.drawable.borde_blanco);
            horarioView.setTextColor(getResources().getColor(android.R.color.black));
            horarioView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(6, 6, 6, 6);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.width = 0;
            horarioView.setLayoutParams(params);

            // Evento de selección
            horarioView.setOnClickListener(v -> {
                // Deseleccionar todos
                for (int i = 0; i < gridHorarios.getChildCount(); i++) {
                    View child = gridHorarios.getChildAt(i);
                    child.setBackgroundResource(R.drawable.borde_blanco);
                }

                v.setBackgroundResource(R.drawable.btn_iniciar_sesion);
                horarioSeleccionado = h;
            });

            gridHorarios.addView(horarioView);
        }
    }

}

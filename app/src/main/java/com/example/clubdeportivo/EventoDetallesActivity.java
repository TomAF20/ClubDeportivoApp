package com.example.clubdeportivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Evento;
import com.example.clubdeportivo.model.PremioDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoDetallesActivity extends AppCompatActivity {

    private TextView tituloEvento, infoEvento;
    private LinearLayout contenedorPremios;
    private Button btnInscribir;
    private Long eventoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_detalle);

        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        long socioId = prefs.getLong("id", -1);
        Toolbar toolbar = findViewById(R.id.toolbar_evento_detalle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        // Vincular vistas
        tituloEvento = findViewById(R.id.titulo_evento);
        infoEvento = findViewById(R.id.info_evento);
        contenedorPremios = findViewById(R.id.contenedor_premios);
        btnInscribir = findViewById(R.id.btn_inscribir);

        // Obtener el ID del evento desde el intent
        eventoId = getIntent().getLongExtra("evento_id", -1);
        if (eventoId == -1) {
            Toast.makeText(this, "Evento no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        cargarEvento(eventoId);

        btnInscribir.setOnClickListener(v -> {
            Intent intent = new Intent(this, JugadoresEventoActivity.class);
            intent.putExtra("evento_id", eventoId);
            intent.putExtra("socio_id", socioId);
            startActivity(intent);
        });
    }

    private void cargarEvento(Long id) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Evento> call = apiService.obtenerEventoPorId(id);

        call.enqueue(new Callback<Evento>() {
            @Override
            public void onResponse(Call<Evento> call, Response<Evento> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Evento evento = response.body();

                    tituloEvento.setText(evento.getNombreEvento());
                    infoEvento.setText(evento.getDescripcion());
                    contenedorPremios.removeAllViews();
                    if (evento.getPremios() != null && !evento.getPremios().isEmpty()) {
                        for (PremioDTO premio : evento.getPremios()) {
                            TextView premioText = new TextView(EventoDetallesActivity.this);
                            premioText.setText("🏆 " + premio.getPosicion() + " - " + premio.getDescripcion());
                            premioText.setTextColor(getColorPorPosicion(premio.getPosicion()));
                            premioText.setTextSize(15f);
                            premioText.setPadding(0, 0, 0, 8);
                            contenedorPremios.addView(premioText);
                        }
                    } else {
                        TextView sinPremios = new TextView(EventoDetallesActivity.this);
                        sinPremios.setText("Este evento no tiene premios registrados.");
                        sinPremios.setTextColor(0xFFFFFFFF);
                        contenedorPremios.addView(sinPremios);
                    }
                } else {
                    Toast.makeText(EventoDetallesActivity.this, "No se pudo cargar el evento", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Evento> call, Throwable t) {
                Log.e("EventoDetalles", "Error de red", t);
                Toast.makeText(EventoDetallesActivity.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getColorPorPosicion(String posicion) {
        switch (posicion) {
            case "1er lugar": return 0xFFFFD700; // dorado
            case "2do lugar": return 0xFFC0C0C0; // plata
            case "3er lugar": return 0xFFCD7F32; // bronce
            default: return 0xFFFFFFFF;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // regresar con flecha
        return true;
    }
}

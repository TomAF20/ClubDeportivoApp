package com.example.clubdeportivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Producto;
import com.example.clubdeportivo.model.ReservaCanchaRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.*;

public class ConfirmarReservaCanchaActivity extends AppCompatActivity {

    private LinearLayout productosContainer;
    private TextView textTotal;
    private Button btnConfirmarReserva;

    private List<Producto> listaProductos = new ArrayList<>();
    private Map<Long, CheckBox> checkboxMap = new HashMap<>();
    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_reserva_cancha);

        productosContainer = findViewById(R.id.productosContainer);
        textTotal = findViewById(R.id.textTotal);
        btnConfirmarReserva = findViewById(R.id.btnConfirmarReserva);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        cargarProductosDesdeAPI();

        btnConfirmarReserva.setOnClickListener(view -> enviarReserva());
    }

    private void cargarProductosDesdeAPI() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.obtenerProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaProductos = response.body();
                    for (Producto producto : listaProductos) {
                        CheckBox checkBox = new CheckBox(ConfirmarReservaCanchaActivity.this);
                        checkBox.setText(producto.getNombre() + " - S/. " + producto.getPrecio());
                        checkBox.setTextColor(Color.WHITE);
                        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                            total += isChecked ? producto.getPrecio() : -producto.getPrecio();
                            textTotal.setText(String.format("TOTAL A PAGAR: S/. %.2f", total));
                        });
                        productosContainer.addView(checkBox);
                        checkboxMap.put(producto.getId(), checkBox);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarReserva() {
        List<Integer> idsSeleccionados = new ArrayList<>();
        for (Map.Entry<Long, CheckBox> entry : checkboxMap.entrySet()) {
            if (entry.getValue().isChecked()) {
                idsSeleccionados.add(entry.getKey().intValue());
            }
        }

        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        long idSocio = prefs.getLong("id", -1);

        ReservaCanchaRequest request = new ReservaCanchaRequest();
        request.setIdSocio((int) idSocio);
        request.setIdCancha(1); // valor fijo o dinámico
        request.setFecha("2025-07-01");
        request.setHoraInicio("10:00");
        request.setHoraFin("11:00");
        request.setProductos(idsSeleccionados);
        request.setTotal(BigDecimal.valueOf(total));

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.crearReserva(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Reserva creada", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ConfirmarReservaCanchaActivity.this, CanchasActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al reservar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

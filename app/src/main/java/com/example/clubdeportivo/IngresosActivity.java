package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngresosActivity extends AppCompatActivity {

    private RecyclerView recyclerIngresos;
    private TextView textTotal;
    private BarChart barChart;
    private PieChart pieChart;

    private IngresoAdapter adapter;
    private ArrayList<Ingreso> ingresos = new ArrayList<>();
    private double totalIngresos = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        // Botones de navegación
        Button btnCanchas = findViewById(R.id.btn_canchas);
        btnCanchas.setOnClickListener(v -> startActivity(new Intent(this, InicioAdminActivity.class)));

        Button btnEmplea = findViewById(R.id.btn_empleados);
        btnEmplea.setOnClickListener(v -> startActivity(new Intent(this, AdminEmpleaActivity.class)));

        Button btnIngresos = findViewById(R.id.btn_ingresos);
        btnIngresos.setOnClickListener(v -> startActivity(new Intent(this, IngresosActivity.class)));

        ImageButton btnAdios = findViewById(R.id.btn_salir);
        btnAdios.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        // Inicialización de vistas
        recyclerIngresos = findViewById(R.id.recycler_ingresos);
        textTotal = findViewById(R.id.text_total_ingresos);
        barChart = findViewById(R.id.bar_chart_ingresos);
        pieChart = findViewById(R.id.pie_chart_ingresos);

        // Configuración del RecyclerView
        recyclerIngresos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngresoAdapter(ingresos);
        recyclerIngresos.setAdapter(adapter);

        // Llamar al backend para obtener los ingresos reales
        cargarDatosDesdeAPI();
    }

    private void cargarDatosDesdeAPI() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<List<Ingreso>> call = apiService.getIngresos();

        call.enqueue(new Callback<List<Ingreso>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Ingreso>> call, Response<List<Ingreso>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ingresos.clear();
                    ingresos.addAll(response.body());

                    totalIngresos = 0.0;
                    for (Ingreso ingreso : ingresos) {
                        totalIngresos += ingreso.getImporte();
                    }

                    textTotal.setText("Total: S/. " + String.format("%.2f", totalIngresos));
                    adapter.notifyDataSetChanged();
                    mostrarGraficoBarras();
                    mostrarGraficoPastel();
                } else {
                    Toast.makeText(IngresosActivity.this, "Error al obtener los ingresos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ingreso>> call, Throwable t) {
                Toast.makeText(IngresosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mostrarGraficoBarras() {
        HashMap<String, Double> ingresosPorMes = new HashMap<>();
        for (Ingreso ingreso : ingresos) {
            String mes = ingreso.getFecha().substring(5, 7); // formato "YYYY-MM-DD"
            ingresosPorMes.put(mes, ingresosPorMes.getOrDefault(mes, 0.0) + ingreso.getImporte());
        }

        String[] primerosSeisMeses = {"01", "02", "03", "04", "05", "06"};
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < primerosSeisMeses.length; i++) {
            String mes = primerosSeisMeses[i];
            float valor = ingresosPorMes.containsKey(mes)
                    ? ingresosPorMes.get(mes).floatValue()
                    : 0f;
            entries.add(new BarEntry(i + 1, valor));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Ingresos por Mes");
        dataSet.setColor(getResources().getColor(android.R.color.holo_blue_light));
        BarData barData = new BarData(dataSet);

        barChart.setData(barData);
        barChart.getDescription().setText("Ingresos por Mes (Ene-Jun)");
        barData.setBarWidth(0.5f);
        barChart.invalidate();
    }

    private void mostrarGraficoPastel() {
        HashMap<String, Double> ingresosPorConcepto = new HashMap<>();
        for (Ingreso ingreso : ingresos) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ingresosPorConcepto.put(
                        ingreso.getConcepto(),
                        ingresosPorConcepto.getOrDefault(ingreso.getConcepto(), 0.0) + ingreso.getImporte()
                );
            }
        }

        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : ingresosPorConcepto.entrySet()) {
            entries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Ingresos por Categoría");
        dataSet.setColors(new int[]{
                R.color.teal_200,
                R.color.purple_200,
                R.color.teal_700,
                R.color.purple_700
        }, this);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setText("Ingresos por Categoría");
        pieChart.invalidate();
    }
}

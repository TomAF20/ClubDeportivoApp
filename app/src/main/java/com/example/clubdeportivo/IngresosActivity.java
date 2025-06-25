package com.example.clubdeportivo;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

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
import java.util.Map;

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

        // Referencias
        recyclerIngresos = findViewById(R.id.recycler_ingresos);
        textTotal = findViewById(R.id.text_total_ingresos);
        barChart = findViewById(R.id.bar_chart_ingresos);
        pieChart = findViewById(R.id.pie_chart_ingresos);

        // DATOS DE EJEMPLO
        ingresos.add(new Ingreso("Alquiler cancha 1", "2025-01-15", 60.00));
        ingresos.add(new Ingreso("Alquiler cancha 2", "2025-01-18", 80.00));
        ingresos.add(new Ingreso("Petos", "2025-01-20", 36.00));
        ingresos.add(new Ingreso("Alquiler cancha 3", "2025-02-10", 70.00));
        ingresos.add(new Ingreso("Balon", "2025-02-15", 10.00));

        // CALCULAR TOTAL
        for (Ingreso ingreso : ingresos) {
            totalIngresos += ingreso.getImporte();
        }
        textTotal.setText("Total: S/. " + String.format("%.2f", totalIngresos));

        // LISTA DE INGRESOS
        recyclerIngresos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngresoAdapter(ingresos);
        recyclerIngresos.setAdapter(adapter);

        // GRÁFICOS
        mostrarGraficoBarras();
        mostrarGraficoPastel();
    }

    private void mostrarGraficoBarras() {
        // 1️⃣ Crear mapa de ingresos por mes
        HashMap<String, Double> ingresosPorMes = new HashMap<>();
        for (Ingreso ingreso : ingresos) {
            String mes = ingreso.getFecha().substring(5, 7);
            ingresosPorMes.put(mes, ingresosPorMes.getOrDefault(mes, 0.0) + ingreso.getImporte());
        }

        // 2️⃣ Definir primeros 6 meses
        String[] primerosSeisMeses = {"01", "02", "03", "04", "05", "06"};
        ArrayList<BarEntry> entries = new ArrayList<>();

        // 3️⃣ Crear entradas para los primeros 6 meses
        for (int i = 0; i < primerosSeisMeses.length; i++) {
            String mes = primerosSeisMeses[i];
            float valor = ingresosPorMes.containsKey(mes)
                    ? ingresosPorMes.get(mes).floatValue()
                    : 0f;
            entries.add(new BarEntry(i + 1, valor));
        }

        // 4️⃣ Crear dataset
        BarDataSet dataSet = new BarDataSet(entries, "Ingresos por Mes");
        dataSet.setColor(getResources().getColor(android.R.color.holo_blue_light));

        // 5️⃣ Asignar al gráfico
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setText("Ingresos por Mes (Ene-Jun)");

        // Opcional: Para que la barra ocupe menos espacio
        barData.setBarWidth(0.5f);

        // 6️⃣ Refrescar gráfico
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

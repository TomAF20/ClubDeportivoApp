package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlStockActivity extends AppCompatActivity {

    private BarChart barChart;
    private PieChart pieChart;
    private TextView tvLista;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_stock);

        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        tvLista = findViewById(R.id.tvLista);

        cargarProductosDesdeApi();

        // Navegación inferior
        LinearLayout btnPerfil = findViewById(R.id.btnPerfil);
        LinearLayout btnInicio = findViewById(R.id.btnInicio);
        LinearLayout btnSalir = findViewById(R.id.btnSalir);

        btnPerfil.setOnClickListener(v -> startActivity(new Intent(this, PerfiliActivity.class)));
        btnInicio.setOnClickListener(v -> startActivity(new Intent(this, InicioLogisticaActivity.class)));
        btnSalir.setOnClickListener(v -> finishAffinity());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarProductosDesdeApi() {
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
        Call<List<Producto>> call = api.obtenerProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Producto> productos = response.body();
                    mostrarLista(productos);
                    cargarGraficoBarras(productos);
                    cargarGraficoCircular(productos);
                } else {
                    Toast.makeText(ControlStockActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ControlStockActivity.this, "Fallo de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarLista(List<Producto> productos) {
        StringBuilder lista = new StringBuilder("Productos:\n");
        for (Producto p : productos) {
            lista.append("- ").append(p.getNombre())
                    .append(": ").append(p.getCantidad_disponible()).append(" unidades\n");
        }
        tvLista.setText(lista.toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarGraficoBarras(List<Producto> productos) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            entries.add(new BarEntry(i, p.getCantidad_disponible()));
            nombres.add(p.getNombre());
        }

        BarDataSet dataSet = new BarDataSet(entries, "Stock disponible");
        dataSet.setColor(getColor(R.color.teal_700));
        dataSet.setValueTextSize(14f);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setTextSize(14f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(nombres));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);

        barChart.animateY(1500);
        barChart.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarGraficoCircular(List<Producto> productos) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        for (Producto p : productos) {
            entries.add(new PieEntry(p.getCantidad_disponible(), p.getNombre()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Distribución de Stock");
        dataSet.setColors(new int[]{
                getColor(R.color.teal_200),
                getColor(R.color.purple_500),
                getColor(R.color.purple_700)
        });
        dataSet.setValueTextSize(14f);
        dataSet.setSliceSpace(3f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setCenterText("Stock");
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        pieChart.animateY(1400);
        pieChart.invalidate();
    }
}

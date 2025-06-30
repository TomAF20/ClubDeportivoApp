package com.example.clubdeportivo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class InicioAdminActivity extends AppCompatActivity {

    private BarChartView barChartView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_admin);

        barChartView = findViewById(R.id.barChartView);

        // alquileres por dia
        float[] ingresosDiarios = {
                250f, 300f, 450f, 370f, 500f, 600f, 420f
        };
        String[] labelsDiarios = {
                "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"
        };
        int[] colors = {
                0xFF4B2C84, // Lunes - Morado
                0xFF2196F3, // Martes - Azul
                0xFFFFC107, // Miércoles - Ámbar
                0xFF4CAF50, // Jueves - Verde
                0xFF9C27B0, // Viernes - Púrpura
                0xFFFF5722, // Sábado - Naranja
                0xFFE91E63  // Domingo - Rosa
        };
        barChartView.setData(ingresosDiarios, labelsDiarios, colors);

        Button btnEmplea = findViewById(R.id.btn_empleados);
        btnEmplea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAdminActivity.this, AdminEmpleaActivity.class);
                startActivity(intent);
            }
        });

        Button btnIngresos = findViewById(R.id.btn_ingresos);
        btnIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAdminActivity.this, IngresosActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnAdios = findViewById(R.id.btn_adios);
        btnAdios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}

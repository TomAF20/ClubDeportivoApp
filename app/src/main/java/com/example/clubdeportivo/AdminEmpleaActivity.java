package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminEmpleaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_empleados);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Eimy", "Capcha", "73956514", "Admin", "eimy.c@clubdeportivo", "#####"));
        empleados.add(new Empleado("Juan", "Castro", "74123456", "Usuario", "juan.c@clubdeportivo", "#####"));
        empleados.add(new Empleado("María", "Pérez", "76234567", "Admin", "maria.p@clubdeportivo", "#####"));

        EmpleadoAdapter adapter = new EmpleadoAdapter(empleados);
        recyclerView.setAdapter(adapter);

        Button btnCanchas = findViewById(R.id.btn_canchas);
        btnCanchas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEmpleaActivity.this, InicioAdminActivity.class);
                startActivity(intent);
            }
        });

        Button btnEmplea = findViewById(R.id.btn_empleados);
        btnEmplea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEmpleaActivity.this, AdminEmpleaActivity.class);
                startActivity(intent);
            }
        });

        Button btnIngresos = findViewById(R.id.btn_ingresos);
        btnIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEmpleaActivity.this, IngresosActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnAdios = findViewById(R.id.btn_adios);
        btnAdios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEmpleaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}

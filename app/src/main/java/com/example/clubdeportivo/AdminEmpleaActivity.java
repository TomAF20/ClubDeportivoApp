package com.example.clubdeportivo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminEmpleaActivity extends AppCompatActivity {
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


    }

}


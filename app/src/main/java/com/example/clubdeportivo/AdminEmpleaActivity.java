package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEmpleaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_empleados);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UsuarioAdapter();
        recyclerView.setAdapter(adapter);

        obtenerUsuarios();

        findViewById(R.id.btn_canchas).setOnClickListener(v -> startActivity(new Intent(this, InicioAdminActivity.class)));
        findViewById(R.id.btn_empleados).setOnClickListener(v -> recreate());
        findViewById(R.id.btn_ingresos).setOnClickListener(v -> startActivity(new Intent(this, IngresosActivity.class)));
        findViewById(R.id.btn_adios).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    private void obtenerUsuarios() {
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
        api.obtenerUsuarios().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    adapter.setUsuarios(response.body());
                } else {
                    Toast.makeText(AdminEmpleaActivity.this, "Error al obtener usuarios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(AdminEmpleaActivity.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
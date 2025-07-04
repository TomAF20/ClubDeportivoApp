package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InicioLogisticaActivity extends AppCompatActivity {

    private Button btnControlStock, btnAgregarProducto, btnBajaProducto;
    private LinearLayout btnPerfil, btnInicio, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_logistica);

        btnControlStock = findViewById(R.id.btnControlStock);
        btnControlStock.setOnClickListener(v -> {
            Intent intent = new Intent(this, ControlStockActivity.class);
            startActivity(intent);
        });;


        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        btnAgregarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarProductoActivity.class);
            startActivity(intent);
        });


        btnBajaProducto = findViewById(R.id.btnBajaProducto);
        btnBajaProducto.setOnClickListener(v -> {
            Intent intent = new Intent(this, BajaProductoActivity.class);
            startActivity(intent);
        });



        btnPerfil = findViewById(R.id.btnPerfil);
        btnInicio = findViewById(R.id.btnInicio);
        btnSalir = findViewById(R.id.btnSalir);

        btnPerfil.setOnClickListener(v -> {
            startActivity(new Intent(this, PerfiliActivity.class));
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
        });


        btnInicio.setOnClickListener(v ->
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show());

        btnSalir.setOnClickListener(v -> {
            Toast.makeText(this, "Saliendo...", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

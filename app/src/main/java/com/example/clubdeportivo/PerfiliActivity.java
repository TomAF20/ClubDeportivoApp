package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfiliActivity extends AppCompatActivity {

    private TextView tvNombreUsuario, tvCorreo, tvRol;
    private Button  btnCerrarSesion;
    private LinearLayout btnPerfil, btnInicio, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfili);

        tvNombreUsuario = findViewById(R.id.tvNombreUsuario);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvRol = findViewById(R.id.tvRol);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        tvNombreUsuario.setText("Eimy Capcha");
        tvCorreo.setText("eimy@logistica.com");
        tvRol.setText("Rol: Logística");


        btnCerrarSesion.setOnClickListener(v -> {
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, InicioLogisticaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnPerfil = findViewById(R.id.btnPerfil);
        btnInicio = findViewById(R.id.btnInicio);
        btnSalir = findViewById(R.id.btnSalir);

        btnPerfil.setOnClickListener(v ->
                Toast.makeText(this, "Ya estás en Perfil", Toast.LENGTH_SHORT).show());

        btnInicio.setOnClickListener(v -> {
            Intent intent = new Intent(this, InicioLogisticaActivity.class);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(v -> {
            finishAffinity(); // Cierra completamente la app
        });
    }
}

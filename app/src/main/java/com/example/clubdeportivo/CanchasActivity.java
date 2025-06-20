package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CanchasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canchas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Canchas");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        Button btnReservar1 = findViewById(R.id.btnReservar1);
        Button btnReservar2 = findViewById(R.id.btnReservar2);
        Button btnReservar3 = findViewById(R.id.btnReservar3);
        Button btnReservar4 = findViewById(R.id.btnReservar4);
        Button btnReservar5 = findViewById(R.id.btnReservar5);
        Button btnReservar6 = findViewById(R.id.btnReservar6);

        View.OnClickListener listener = v -> {
            Intent intent = new Intent(CanchasActivity.this, CanchaDetalleActivity.class);
            startActivity(intent);
        };

        btnReservar1.setOnClickListener(listener);
        btnReservar2.setOnClickListener(listener);
        btnReservar3.setOnClickListener(listener);
        btnReservar4.setOnClickListener(listener);
        btnReservar5.setOnClickListener(listener);
        btnReservar6.setOnClickListener(listener);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_canchas);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_eventos) {
                startActivity(new Intent(this, EventosActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, PerfilActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ChatActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(this, ReporteActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return id == R.id.nav_canchas;
        });
    }
}

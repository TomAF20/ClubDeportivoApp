package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EquiposEventoActivity extends AppCompatActivity {

    private Button btnVolver;
    private ImageButton btnBackEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipos_evento);

        btnVolver = findViewById(R.id.btnVolver);
        btnBackEquipos = findViewById(R.id.btnBackEquipos);

        View.OnClickListener volverListener = v -> {
            Intent intent = new Intent(this, EventosActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        };

        btnVolver.setOnClickListener(volverListener);
        btnBackEquipos.setOnClickListener(volverListener);
    }
}
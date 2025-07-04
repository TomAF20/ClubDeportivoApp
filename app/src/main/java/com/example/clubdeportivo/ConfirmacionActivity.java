package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {

    private TextView tvMensajeConfirmacion;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confimacion);

        tvMensajeConfirmacion = findViewById(R.id.tvMensajeConfirmacion);
        btnVolver = findViewById(R.id.btnVolver);

        // Mostrar mensaje personalizado si se recibe
        String mensaje = getIntent().getStringExtra("mensaje");
        if (mensaje != null && !mensaje.isEmpty()) {
            tvMensajeConfirmacion.setText(mensaje);
        }

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, InicioLogisticaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}

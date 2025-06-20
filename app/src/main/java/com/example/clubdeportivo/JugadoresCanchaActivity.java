package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.core.content.ContextCompat;


import androidx.appcompat.app.AppCompatActivity;

public class JugadoresCanchaActivity extends AppCompatActivity {

    Spinner spinnerCantidad;
    LinearLayout contenedorJugadores;
    Button btnSiguiente;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_cancha);

        spinnerCantidad = findViewById(R.id.spinnerCantidad);
        contenedorJugadores = findViewById(R.id.contenedorJugadores);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnBack = findViewById(R.id.btnBack);

        String[] opciones = new String[22];
        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = (i + 1) + " jugadores";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        spinnerCantidad.setAdapter(adapter);

        spinnerCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int cantidad = position + 1;
                generarCamposJugadores(cantidad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(JugadoresCanchaActivity.this, ConfirmarReservaCanchaActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void generarCamposJugadores(int cantidad) {
        contenedorJugadores.removeAllViews();

        for (int i = 1; i <= cantidad; i++) {
            EditText nombre = new EditText(this);
            nombre.setHint("Jugador " + i + " - Nombre");
            nombre.setBackgroundResource(R.drawable.campo_texto_blanco);
            nombre.setPadding(30, 30, 30, 30);
            nombre.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            nombre.setHintTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp1.setMargins(0, 0, 0, 12);
            nombre.setLayoutParams(lp1);

            EditText numero = new EditText(this);
            numero.setHint("Jugador " + i + " - Número");
            numero.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            numero.setBackgroundResource(R.drawable.campo_texto_blanco);
            numero.setPadding(30, 30, 30, 30);
            numero.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            numero.setHintTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2.setMargins(0, 0, 0, 20);
            numero.setLayoutParams(lp2);

            contenedorJugadores.addView(nombre);
            contenedorJugadores.addView(numero);
        }
    }
}

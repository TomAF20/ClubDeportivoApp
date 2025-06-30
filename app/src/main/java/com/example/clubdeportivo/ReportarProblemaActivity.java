package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReportarProblemaActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText etReporte;
    private Button btnReportar;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.arbitro_reportar_problema);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinnerTipoIncidencia);
        etReporte = findViewById(R.id.etReporte);
        btnReportar = findViewById(R.id.btnReportar);
        tableLayout = findViewById(R.id.tableLayoutReportes); // ⚠️ ID que debes poner en XML

        // Adaptador del spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_incidencia,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnReportar.setOnClickListener(view -> {
            String tipo = spinner.getSelectedItem().toString();
            String descripcion = etReporte.getText().toString().trim();

            if (tipo.equals("Seleccionar Tipo de Incidencia") || descripcion.isEmpty()) {
                Toast.makeText(this, "Completa los campos correctamente", Toast.LENGTH_SHORT).show();
                return;
            }

            agregarFilaAReporte(descripcion, tipo);
            etReporte.setText(""); // limpiar campo
            spinner.setSelection(0); // reset spinner
        });


        Toolbar toolbar = findViewById(R.id.toolbar_reportes);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Árbitro");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_reportar_arbitro);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_chat_arbitro) {
                startActivity(new Intent(this, ChatArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_historial_arbitro) {
                startActivity(new Intent(this, HistorialActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_partidos) {
                startActivity(new Intent(this, ArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_perfil_arbitro) {
                startActivity(new Intent(this, PerfilArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return id == R.id.nav_reportar_arbitro;
        });

    }

    private void agregarFilaAReporte(String reporte, String tipo) {
        TableRow nuevaFila = new TableRow(this);

        // Texto del reporte
        TextView tvReporte = new TextView(this);
        tvReporte.setText(reporte);
        tvReporte.setPadding(8, 8, 8, 8);
        tvReporte.setTextSize(14); // tamaño pequeño

        // Texto del tipo
        TextView tvTipo = new TextView(this);
        tvTipo.setText(tipo);
        tvTipo.setPadding(8, 8, 8, 8);
        tvTipo.setTextSize(14); // tamaño pequeño

        // Spinner de estado con texto más pequeño
        Spinner spinnerEstado = new Spinner(this, Spinner.MODE_DROPDOWN);
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Pendiente", "Resuelto"}
        );
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(estadoAdapter);

        // Reducir tamaño visual del spinner
        spinnerEstado.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        spinnerEstado.setPadding(8, 0, 8, 0);
        spinnerEstado.setMinimumWidth(150); // ancho mínimo
        spinnerEstado.setScaleX(0.9f);
        spinnerEstado.setScaleY(0.9f);

        // Botón "X" más pequeño
        Button btnEliminar = new Button(this);
        btnEliminar.setText("X");
        btnEliminar.setTextSize(12);
        btnEliminar.setPadding(4, 4, 4, 4);
        btnEliminar.setMinWidth(0);
        btnEliminar.setMinimumWidth(0);
        btnEliminar.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        btnEliminar.setTextColor(getResources().getColor(android.R.color.white));

        btnEliminar.setOnClickListener(v -> tableLayout.removeView(nuevaFila));

        // Añadir vistas a la fila
        nuevaFila.addView(tvReporte);
        nuevaFila.addView(tvTipo);
        nuevaFila.addView(spinnerEstado);
        nuevaFila.addView(btnEliminar);

        tableLayout.addView(nuevaFila);
    }



}

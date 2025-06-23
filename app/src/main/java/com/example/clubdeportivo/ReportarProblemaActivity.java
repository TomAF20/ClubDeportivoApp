package com.example.clubdeportivo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReportarProblemaActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btnReportar;

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

        // 🔹 Referencias UI
        spinner = findViewById(R.id.spinnerTipoIncidencia);
        btnReportar = findViewById(R.id.btnReportar);

        // 🔹 Cargar el Spinner con el array desde strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_incidencia,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 🔹 Acción del botón
        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seleccion = spinner.getSelectedItem().toString();

                if (seleccion.equals("Seleccionar Tipo de Incidencia")) {
                    Toast.makeText(ReportarProblemaActivity.this, "Por favor selecciona un tipo válido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReportarProblemaActivity.this, "Reporte enviado: " + seleccion, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

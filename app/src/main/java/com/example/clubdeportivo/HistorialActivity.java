package com.example.clubdeportivo;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class HistorialActivity extends AppCompatActivity {

    private EditText etBuscar;
    private TableLayout tableLayout;
    private List<Partido> listaPartidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arbitro_historial);

        Toolbar toolbar = findViewById(R.id.toolbar_historial);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Árbitro");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_historial_arbitro);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_chat_arbitro) {
                startActivity(new Intent(this, ChatArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_partidos) {
                startActivity(new Intent(this, ArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_reportar_arbitro) {
                startActivity(new Intent(this, ReportarProblemaActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_perfil_arbitro) {
                startActivity(new Intent(this, PerfilArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return id == R.id.nav_historial_arbitro;
        });

        etBuscar = findViewById(R.id.etBuscar);
        tableLayout = findViewById(R.id.tableLayoutPartidos); // le pondremos un ID al TableLayout

        listaPartidos = new ArrayList<>();
        listaPartidos.add(new Partido("Equipo A vs B", "16:00", "18:00"));
        listaPartidos.add(new Partido("Equipo C vs D", "18:30", "20:30"));
        listaPartidos.add(new Partido("Equipo E vs F", "15:00", "17:00"));

        mostrarPartidosEnTabla(listaPartidos);

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(view -> {
            String texto = etBuscar.getText().toString().toLowerCase().trim();
            List<Partido> filtrados = filtrarPartidos(texto);
            mostrarPartidosEnTabla(filtrados);
        });
    }

    private void mostrarPartidosEnTabla(List<Partido> partidos) {
        tableLayout.removeViews(1, tableLayout.getChildCount() - 1); // Borra todo menos el encabezado

        for (Partido p : partidos) {
            TableRow fila = new TableRow(this);

            TextView tv1 = new TextView(this);
            tv1.setText(p.getNombre());
            tv1.setPadding(8, 8, 8, 8);

            TextView tv2 = new TextView(this);
            tv2.setText(p.getHoraInicio());
            tv2.setPadding(8, 8, 8, 8);

            TextView tv3 = new TextView(this);
            tv3.setText(p.getHoraFin());
            tv3.setPadding(8, 8, 8, 8);

            fila.addView(tv1);
            fila.addView(tv2);
            fila.addView(tv3);

            tableLayout.addView(fila);
        }
    }

    private List<Partido> filtrarPartidos(String texto) {
        List<Partido> filtrados = new ArrayList<>();
        for (Partido p : listaPartidos) {
            if (p.getNombre().toLowerCase().contains(texto) ||
                    p.getHoraInicio().contains(texto) ||
                    p.getHoraFin().contains(texto)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    // Clase simple para representar un partido
    public static class Partido {
        private final String nombre;
        private final String horaInicio;
        private final String horaFin;

        public Partido(String nombre, String horaInicio, String horaFin) {
            this.nombre = nombre;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
        }

        public String getNombre() { return nombre; }
        public String getHoraInicio() { return horaInicio; }
        public String getHoraFin() { return horaFin; }
    }
}


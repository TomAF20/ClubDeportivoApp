package com.example.clubdeportivo;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ConversacionArbitroActivity extends AppCompatActivity {

    private List<String> mensajes;
    private MensajeAdapter adapter;
    private EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_conversacion_arbitro);

        Toolbar toolbar = findViewById(R.id.toolbar_conversacion_arbitro);
        setSupportActionBar(toolbar);

        String nombre = getIntent().getStringExtra("nombre");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(nombre != null ? nombre : "Chat");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        mensajes = new ArrayList<>();
        mensajes.add("Hola, bienvenido al chat con " + nombre);

<<<<<<< HEAD
        RecyclerView recycler = findViewById(R.id.recyclerMensajes);
=======
        RecyclerView recycler = findViewById(R.id.recyclerMensajesArbitro);
>>>>>>> origin/main
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MensajeAdapter(mensajes);
        recycler.setAdapter(adapter);

        etMensaje = findViewById(R.id.etMensaje);
        ImageButton btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(v -> {
            String texto = etMensaje.getText().toString().trim();
            if (!texto.isEmpty()) {
                mensajes.add(texto);
                adapter.notifyItemInserted(mensajes.size() - 1);
                etMensaje.setText("");
                recycler.scrollToPosition(mensajes.size() - 1);
            }
        });
    }
}
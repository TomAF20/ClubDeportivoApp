package com.example.clubdeportivo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BajaProductoActivity extends AppCompatActivity {

    private EditText etIdBaja, etMotivoBaja;
    private Button btnConfirmarBaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baja_productos);

        etIdBaja = findViewById(R.id.etIdBaja);
        etMotivoBaja = findViewById(R.id.etMotivoBaja);
        btnConfirmarBaja = findViewById(R.id.btnConfirmarBaja);

        btnConfirmarBaja.setOnClickListener(v -> {
            try {
                Long id = Long.parseLong(etIdBaja.getText().toString());
                String motivo = etMotivoBaja.getText().toString();

                BajaProducto baja = new BajaProducto(id, motivo);

                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
                Call<Void> call = apiService.darDeBaja(baja);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(BajaProductoActivity.this, "Producto dado de baja correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BajaProductoActivity.this, ConfirmacionActivity.class);
                            intent.putExtra("mensaje", "Producto dado de baja correctamente.");
                            startActivity(intent);
                        } else {
                            Toast.makeText(BajaProductoActivity.this, "Error al dar de baja: " + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("BAJA_ERROR", "Fallo de red", t);
                        Toast.makeText(BajaProductoActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            } catch (Exception e) {
                Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
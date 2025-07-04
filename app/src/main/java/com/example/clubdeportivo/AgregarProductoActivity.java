package com.example.clubdeportivo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText etIdProducto, etNombreProducto, etPrecioProducto, etCantidadProducto;
    private Button btnGuardarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_producto);

        etIdProducto = findViewById(R.id.etIdProducto);
        etNombreProducto = findViewById(R.id.etNombreProducto);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        etCantidadProducto = findViewById(R.id.etCantidadProducto);
        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);

        btnGuardarProducto.setOnClickListener(v -> {
            try {
                long id = Long.parseLong(etIdProducto.getText().toString());
                String nombre = etNombreProducto.getText().toString();
                double precio = Double.parseDouble(etPrecioProducto.getText().toString());
                int cantidad = Integer.parseInt(etCantidadProducto.getText().toString());

                Producto producto = new Producto(id, nombre, precio, cantidad);

                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
                Call<Producto> call = apiService.guardarProducto(producto);

                call.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AgregarProductoActivity.this, "Producto guardado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AgregarProductoActivity.this, ConfirmacionActivity.class);
                            intent.putExtra("mensaje", "Producto guardado correctamente.");
                            startActivity(intent);
                        } else {
                            Toast.makeText(AgregarProductoActivity.this, "Error al guardar: " + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                        Log.e("RETROFIT_ERROR", "Fallo de red", t);
                        Toast.makeText(AgregarProductoActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            } catch (Exception e) {
                Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        // Botones inferiores
        LinearLayout btnPerfil = findViewById(R.id.btnPerfil);
        LinearLayout btnInicio = findViewById(R.id.btnInicio);
        LinearLayout btnSalir = findViewById(R.id.btnSalir);

        btnPerfil.setOnClickListener(v -> startActivity(new Intent(this, PerfiliActivity.class)));
        btnInicio.setOnClickListener(v -> startActivity(new Intent(this, InicioLogisticaActivity.class)));
        btnSalir.setOnClickListener(v -> finishAffinity());
    }
}
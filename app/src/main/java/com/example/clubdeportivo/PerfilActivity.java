package com.example.clubdeportivo;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.Usuario;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    private EditText etNombres, etApellidoPaterno, etApellidoMaterno, etFechaNacimiento,
            etDni, etCelular, etCorreo;
    private Button btnEditar, btnGuardar;
    private ImageView imgPerfil, qrImage;
    private TextView btnElegirAvatar;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Toolbar toolbar = findViewById(R.id.toolbar_perfil);
        setSupportActionBar(toolbar);

        // UI
        etNombres = findViewById(R.id.etNombres);
        etApellidoPaterno = findViewById(R.id.etApellidoPaterno);
        etApellidoMaterno = findViewById(R.id.etApellidoMaterno);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etDni = findViewById(R.id.etDni);
        etCelular = findViewById(R.id.etCelular);
        etCorreo = findViewById(R.id.etCorreo);
        btnEditar = findViewById(R.id.btnEditar);
        btnGuardar = findViewById(R.id.btnGuardar);
        imgPerfil = findViewById(R.id.imgPerfil);
        btnElegirAvatar = findViewById(R.id.btnElegirAvatar);
        qrImage = findViewById(R.id.qrImage);

        // Obtener nombre de usuario guardado
        String nombreUsuario = getSharedPreferences("loginPrefs", MODE_PRIVATE)
                .getString("nombreUsuario", "");

        // Llamada a API
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getUsuario(nombreUsuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    usuarioActual = response.body();
                    llenarCampos(usuarioActual);
                    generarQR();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(PerfilActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
            }
        });

        btnElegirAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        btnEditar.setOnClickListener(v -> {
            alternarCampos(true);
            btnEditar.setVisibility(View.GONE);
            btnGuardar.setVisibility(View.VISIBLE);
        });

        btnGuardar.setOnClickListener(v -> {
            alternarCampos(false);
            btnGuardar.setVisibility(View.GONE);
            btnEditar.setVisibility(View.VISIBLE);
            generarQR(); // regenerar QR con los nuevos datos visibles
        });

        ImageButton btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> {

                    getSharedPreferences("loginPrefs", MODE_PRIVATE).edit().clear().apply();

                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_perfil);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_canchas) {
                startActivity(new Intent(this, CanchasActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_eventos) {
                startActivity(new Intent(this, EventosActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_chat) {
                startActivity(new Intent(this, ChatActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_feedback) {
                startActivity(new Intent(this, ReporteActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_perfil) {
                return true;
            }
            return false;
        });
    }

    private void llenarCampos(Usuario usuario) {
        etNombres.setText(usuario.getNombre());
        etApellidoPaterno.setText(usuario.getApellidoPaterno());
        etApellidoMaterno.setText(usuario.getApellidoMaterno());
        etFechaNacimiento.setText(usuario.getFechaNacimiento());
        etDni.setText(usuario.getDni());
        etCelular.setText(usuario.getCelular());
        etCorreo.setText(usuario.getCorreo());
    }

    private void alternarCampos(boolean editable) {
        etNombres.setEnabled(editable);
        etApellidoPaterno.setEnabled(editable);
        etApellidoMaterno.setEnabled(editable);
        etFechaNacimiento.setEnabled(editable);
        etDni.setEnabled(editable);
        etCelular.setEnabled(editable);
        etCorreo.setEnabled(editable);
    }

    private void generarQR() {
        if (usuarioActual == null) return;

        String datosQR = "ID: " + usuarioActual.getId() + "\nNombre: " + usuarioActual.getNombre()
                + "\nDNI: " + usuarioActual.getDni() + "\nCorreo: " + usuarioActual.getCorreo();

        QRCodeWriter qrWriter = new QRCodeWriter();
        try {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x / 2;

            com.google.zxing.common.BitMatrix bitMatrix =
                    qrWriter.encode(datosQR, BarcodeFormat.QR_CODE, width, width);

            Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < width; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            qrImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imgPerfil.setImageURI(imageUri);
        }
    }
}


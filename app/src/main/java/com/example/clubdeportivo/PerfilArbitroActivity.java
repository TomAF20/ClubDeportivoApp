package com.example.clubdeportivo;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

public class PerfilArbitroActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etNombres, etApellidoPaterno, etApellidoMaterno, etFechaNacimiento,
            etDni, etCelular, etCorreo;
    private Button btnEditar, btnGuardar;
    private ImageView imgPerfil, qrImage;
    private TextView btnElegirAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_arbitro);

        Toolbar toolbar = findViewById(R.id.toolbar_perfil_arbitro);
        setSupportActionBar(toolbar);

        ImageButton btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show());

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

        generarQR();

        btnElegirAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
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
            generarQR(); // regenerar QR con los nuevos datos
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_perfil_arbitro);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_partidos) {
                startActivity(new Intent(this, ArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_chat_arbitro) {
                startActivity(new Intent(this, ChatArbitroActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_reportar_arbitro) {
                startActivity(new Intent(this, ReportarProblemaActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_historial_arbitro) {
                startActivity(new Intent(this, HistorialActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;

            }else if (id == R.id.nav_perfil_arbitro) {
                return true;
            }
            return false;
        });
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
        String datosQR = "Nombre: " + etNombres.getText().toString()
                + "\nDNI: " + etDni.getText().toString()
                + "\nCorreo: " + etCorreo.getText().toString();

        QRCodeWriter qrWriter = new QRCodeWriter();
        try {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x / 2;

            Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565);
            com.google.zxing.common.BitMatrix bitMatrix = qrWriter.encode(datosQR, BarcodeFormat.QR_CODE, width, width);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < width; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imgPerfil.setImageURI(imageUri);
        }
    }
}

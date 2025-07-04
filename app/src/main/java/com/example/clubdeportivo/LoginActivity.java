package com.example.clubdeportivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clubdeportivo.api.ApiService;
import com.example.clubdeportivo.api.RetrofitClient;
import com.example.clubdeportivo.model.LoginRequest;
import com.example.clubdeportivo.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsuario, inputPassword;
    private Button btnLoginGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inputUsuario = findViewById(R.id.inputUsuario);
        inputPassword = findViewById(R.id.inputPassword);
        btnLoginGo = findViewById(R.id.btnLoginGo);

        btnLoginGo.setOnClickListener(view -> {
            String usuario = inputUsuario.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Completa ambos campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Accesos hardcodeados para pruebas locales
            if (usuario.equals("tom") && password.equals("1234")) {
                startActivity(new Intent(LoginActivity.this, CanchasActivity.class));
                finish();
                return;
            } else if (usuario.equals("eimy") && password.equals("1234")) {
                startActivity(new Intent(LoginActivity.this, InicioAdminActivity.class));
                finish();
                return;
            } else if (usuario.equals("yair") && password.equals("1234")) {
                startActivity(new Intent(LoginActivity.this, ArbitroActivity.class));
                finish();
                return;
            } else if (usuario.equals("doc") && password.equals("1234")) {
                startActivity(new Intent(LoginActivity.this, RegistroMedicoActivity.class));
                finish();
                return;
            } else if (usuario.equals("logistica") && password.equals("1234")) {
                startActivity(new Intent(LoginActivity.this, InicioLogisticaActivity.class));
                finish();
                return;
            }

            // Si no está hardcodeado, intenta con la API
            Log.d("LOGIN_REQUEST", "Usuario: " + usuario + ", Password: " + password);

            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            LoginRequest request = new LoginRequest(usuario, password);
            Call<Usuario> call = apiService.login(request);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Usuario u = response.body();
                        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("nombreUsuario", u.getNombreUsuario());
                        editor.putString("rol", u.getRol());
                        editor.putLong("id", u.getId());
                        editor.apply();
                        String rol = u.getRol().toLowerCase();

                        switch (rol) {
                            case "admin":
                                startActivity(new Intent(LoginActivity.this, InicioAdminActivity.class));
                                break;
                            case "arbitro":
                                startActivity(new Intent(LoginActivity.this, ArbitroActivity.class));
                                break;
                            case "socio":
                                startActivity(new Intent(LoginActivity.this, CanchasActivity.class));
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "Rol no permitido", Toast.LENGTH_SHORT).show();
                                return;
                        }

                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}

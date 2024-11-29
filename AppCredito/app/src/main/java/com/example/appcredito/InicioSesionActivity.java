package com.example.appcredito;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionActivity extends AppCompatActivity {
    EditText etUsernameLogin, etPasswordLogin;
    Button btnLogin;
    TextView tvGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etUsernameLogin = findViewById(R.id.etUsernameLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsernameLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedUsername = prefs.getString("username", null);
                String savedPassword = prefs.getString("password", null);

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    Toast.makeText(InicioSesionActivity.this, "Inicio de sesión correcta, bienvenido.", Toast.LENGTH_SHORT).show();

                    // Redirigir a MenuActivity
                    startActivity(new Intent(InicioSesionActivity.this, MenuActivity.class));
                } else {
                    Toast.makeText(InicioSesionActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicioSesionActivity.this, RegistroActivity.class));
            }
        });
    }
}

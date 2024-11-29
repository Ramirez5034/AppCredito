package com.example.appcredito;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {
    EditText etUsernameRegister, etPasswordRegister;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsernameRegister.getText().toString();
                String password = etPasswordRegister.getText().toString();

                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(RegistroActivity.this, "El registro es correcto.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistroActivity.this, InicioSesionActivity.class));
            }
        });
    }
}

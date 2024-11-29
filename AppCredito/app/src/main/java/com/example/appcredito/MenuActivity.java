package com.example.appcredito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnVolverInicioSesion = findViewById(R.id.btnVolverInicioSesion);
        Button btnVolverConsulta = findViewById(R.id.btnVolverConsulta);
        Button btnSalir = findViewById(R.id.btnSalir);
        Button btnCarritoCompras = findViewById(R.id.btnCarritoCompras);
        Button btnSolicitarCredito = findViewById(R.id.btnSolicitarCredito);

        // Volver al inicio de sesión
        btnVolverInicioSesion.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, InicioSesionActivity.class);
            startActivity(intent);
            finish();
        });

        // Ir a consulta
        btnVolverConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, InicioConsultaActivity.class);
            startActivity(intent);
        });

        // Salir de la aplicación
        btnSalir.setOnClickListener(v -> {
            new AlertDialog.Builder(MenuActivity.this)
                    .setTitle("Confirmación")
                    .setMessage("¿Desea salir?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity()) // Cierra toda la app
                    .setNegativeButton("No", null)
                    .show();
        });

        // Ir al carrito de compras
        btnCarritoCompras.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CarritoComprasActivity.class);
            startActivity(intent);
        });

        // Ir a solicitar crédito
        btnSolicitarCredito.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, SolicitarAumentoActivity.class);
            startActivity(intent);
        });
    }
}
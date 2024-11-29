package com.example.appcredito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InicioConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Inicializar los botones
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnCarrito = findViewById(R.id.btnCarrito);
        Button btnSolicitarCredito = findViewById(R.id.btnSolicitarCredito);

        // Configurar acción para el botón "Volver al Menú"
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes usar finish() para volver a la actividad anterior, o navegar a un menú
                finish();  // Esto cerrará la actividad actual y volverá a la actividad anterior
            }
        });

        // Configurar acción para el botón "Ir al Carrito"
        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir al formulario del carrito
                Intent intent = new Intent(InicioConsultaActivity.this, CarritoComprasActivity.class);
                startActivity(intent);
            }
        });

        // Configurar acción para el botón "Solicitar Aumento de Crédito"
        btnSolicitarCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir al formulario de solicitar aumento de crédito
                Intent intent = new Intent(InicioConsultaActivity.this, SolicitarAumentoActivity.class);
                startActivity(intent);
            }
        });
    }
}


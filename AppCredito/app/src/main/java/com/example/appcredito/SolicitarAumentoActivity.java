package com.example.appcredito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.database.Cursor;  // IMPORTACIÓN DE CURSOR
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SolicitarAumentoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_COMPROBANTE = 1;
    private static final int REQUEST_CODE_INE = 2;

    private EditText edtComprobanteDomicilio;
    private EditText edtINE;
    private ImageView imgINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_aumento);

        edtComprobanteDomicilio = findViewById(R.id.edtComprobanteDomicilio);
        edtINE = findViewById(R.id.edtINE);
        imgINE = findViewById(R.id.imgINE);

        Button btnComprobanteDomicilio = findViewById(R.id.btnComprobanteDomicilio);
        Button btnINE = findViewById(R.id.btnINE);
        Button btnAceptar = findViewById(R.id.btnAceptar);
        Button btnMenu = findViewById(R.id.btnMenu);

        btnComprobanteDomicilio.setOnClickListener(v -> openFilePicker(REQUEST_CODE_COMPROBANTE, "application/pdf"));

        btnINE.setOnClickListener(v -> openFilePicker(REQUEST_CODE_INE, "image/*"));

        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(SolicitarAumentoActivity.this, MenuActivity.class);  // Cambia MenuActivity a la actividad de tu menú
            startActivity(intent);  // Esto abriría la actividad del menú sin cerrar la actual
        });

        // Acción cuando se presiona el botón de "Aceptar" (Enviar solicitud)
        btnAceptar.setOnClickListener(v -> {
            // Verificar si ambos campos tienen un archivo
            if (edtComprobanteDomicilio.getText().toString().isEmpty() || edtINE.getText().toString().isEmpty()) {
                // Si falta alguno de los archivos, mostrar un mensaje de error
                new AlertDialog.Builder(SolicitarAumentoActivity.this)
                        .setTitle("Error")
                        .setMessage("Favor de ingresar ambos archivos: el comprobante de domicilio y la imagen de INE o fotografía.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                // Si ambos archivos están presentes, mostrar un mensaje de confirmación
                new AlertDialog.Builder(SolicitarAumentoActivity.this)
                        .setTitle("Solicitud Enviada")
                        .setMessage("La solicitud en aumento de credito a sido enviado correctamente. Favor de esperar a la aprobación.")
                        .setPositiveButton("OK", null)  // Eliminé la acción de finish() para que la actividad no se cierre
                        .show();
            }
        });

    }


    // Método para abrir el selector de archivos
    private void openFilePicker(int requestCode, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        startActivityForResult(Intent.createChooser(intent, "Seleccione un archivo"), requestCode);
    }

    // Obtener el nombre del archivo seleccionado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String fileName = getFileName(uri);

                if (requestCode == REQUEST_CODE_COMPROBANTE) {
                    edtComprobanteDomicilio.setText(fileName);
                } else if (requestCode == REQUEST_CODE_INE) {
                    edtINE.setText(fileName);
                    imgINE.setImageURI(uri); // Muestra la imagen en el ImageView
                }
            }
        }
    }

    // Obtener el nombre del archivo a partir de la URI
    private String getFileName(Uri uri) {
        String fileName = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex != -1 && cursor.moveToFirst()) {
                fileName = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return fileName;
    }
}

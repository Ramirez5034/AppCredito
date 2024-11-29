package com.example.appcredito;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CarritoComprasActivity extends AppCompatActivity {

    private ListView listViewCarrito;
    private ArrayList<HashMap<String, String>> carritoProductos;
    private HashMap<String, Producto> productosCarrito;
    private SimpleAdapter adapter;
    private TextView totalTextView;  // TextView para mostrar el total

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);

        // Inicialización de vistas y estructuras
        listViewCarrito = findViewById(R.id.listViewCarrito);
        totalTextView = findViewById(R.id.textTotal);  // Inicializamos el TextView para el total
        carritoProductos = new ArrayList<>();
        productosCarrito = new HashMap<>();

        // Definir columnas de datos y el layout personalizado
        String[] from = {"producto", "cantidad", "precio"};
        int[] to = {R.id.textProducto, R.id.textCantidad, R.id.textPrecio};

        // Adaptador para la ListView
        adapter = new SimpleAdapter(this, carritoProductos, R.layout.list_item_producto, from, to);
        listViewCarrito.setAdapter(adapter);

        // Botones
        Button btnProducto1 = findViewById(R.id.btnProducto1);
        Button btnProducto2 = findViewById(R.id.btnProducto2);
        Button btnProducto3 = findViewById(R.id.btnProducto3);
        Button btnDisminuirCantidad = findViewById(R.id.btnDisminuirCantidad);
        Button btnEliminarProducto = findViewById(R.id.btnEliminarProducto);
        Button btnVaciarCarrito = findViewById(R.id.btnVaciarCarrito);
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnRegresarConsulta = findViewById(R.id.btnRegresarConsulta);

        // Agregar productos al carrito
        btnProducto1.setOnClickListener(v -> agregarProducto("Consola GameCube", 1000));
        btnProducto2.setOnClickListener(v -> agregarProducto("Nintendo Switch", 1890));
        btnProducto3.setOnClickListener(v -> agregarProducto("PlayStation 5", 2500));

        // Disminuir la cantidad del producto seleccionado
        btnDisminuirCantidad.setOnClickListener(v -> disminuirCantidad());

        // Eliminar el producto seleccionado
        btnEliminarProducto.setOnClickListener(v -> eliminarProductoSeleccionado());

        // Vaciar el carrito
        btnVaciarCarrito.setOnClickListener(v -> vaciarCarrito());

        // Regresar al menú principal
        btnMenu.setOnClickListener(v -> finish());

        // Regresar a la actividad de consulta
        btnRegresarConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(CarritoComprasActivity.this, InicioConsultaActivity.class);
            startActivity(intent);
        });

        // Modificar producto desde la tabla
        listViewCarrito.setOnItemClickListener((parent, view, position, id) -> {
            String productoSeleccionado = carritoProductos.get(position).get("producto");
            Producto producto = productosCarrito.get(productoSeleccionado);

            if (producto != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CarritoComprasActivity.this);
                builder.setTitle("Modificar " + productoSeleccionado)
                        .setMessage("Seleccione una opción")
                        .setPositiveButton("Disminuir Cantidad", (dialog, which) -> {
                            if (producto.getCantidad() > 1) {
                                producto.decrementarCantidad();
                            } else {
                                productosCarrito.remove(productoSeleccionado);
                            }
                            actualizarCarrito();
                        })
                        .setNegativeButton("Eliminar Producto", (dialog, which) -> {
                            productosCarrito.remove(productoSeleccionado);
                            actualizarCarrito();
                        })
                        .setNeutralButton("Cancelar", null)
                        .show();
            }
        });
    }

    // Método para agregar productos al carrito con precio
    private void agregarProducto(String producto, int precio) {
        if (productosCarrito.containsKey(producto)) {
            productosCarrito.get(producto).incrementarCantidad();
        } else {
            productosCarrito.put(producto, new Producto(producto, 1, precio));
        }

        actualizarCarrito();  // Actualizar la vista de la lista
    }

    // Método para disminuir la cantidad del producto seleccionado
    private void disminuirCantidad() {
        if (!productosCarrito.isEmpty()) {
            Producto producto = (Producto) productosCarrito.values().toArray()[0];
            if (producto.getCantidad() > 1) {
                producto.decrementarCantidad();
                actualizarCarrito();
                Toast.makeText(this, producto.getNombre() + " cantidad disminuida", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se puede disminuir más la cantidad de " + producto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para eliminar el producto seleccionado
    private void eliminarProductoSeleccionado() {
        if (!productosCarrito.isEmpty()) {
            String producto = productosCarrito.keySet().iterator().next();
            productosCarrito.remove(producto);
            actualizarCarrito();
            Toast.makeText(this, producto + " eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
        }
    }

    // Vaciar todo el carrito
    private void vaciarCarrito() {
        productosCarrito.clear();
        actualizarCarrito();
        Toast.makeText(this, "Carrito vaciado", Toast.LENGTH_SHORT).show();
    }

    // Actualizar la ListView con los productos en el carrito y mostrar el precio total
    private void actualizarCarrito() {
        carritoProductos.clear();
        double total = 0;

        for (Producto producto : productosCarrito.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("producto", producto.getNombre());
            map.put("cantidad", String.valueOf(producto.getCantidad()));
            map.put("precio", String.valueOf(producto.getCantidad() * producto.getPrecio()));
            carritoProductos.add(map);

            total += producto.getCantidad() * producto.getPrecio();
        }

        totalTextView.setText("Total: $" + total);  // Mostrar total en el TextView
        adapter.notifyDataSetChanged();  // Actualizar la vista
    }
}

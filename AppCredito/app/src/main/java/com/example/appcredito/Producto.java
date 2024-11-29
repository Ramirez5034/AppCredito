package com.example.appcredito;

// Producto.java
public class Producto {
    private String nombre;
    private int cantidad;
    private int precio;

    public Producto(String nombre, int cantidad, int precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public void decrementarCantidad() {
        this.cantidad--;
    }

    public int getPrecio() {
        return precio;
    }
}

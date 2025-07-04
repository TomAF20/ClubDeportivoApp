package com.example.clubdeportivo;

public class Producto {
    private long id;
    private String nombre;
    private double precio;
    private int cantidad_disponible;

    public Producto() {}

    public Producto(long id, String nombre, double precio, int cantidad_disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad_disponible() { return cantidad_disponible; }
    public void setCantidad_disponible(int cantidad_disponible) { this.cantidad_disponible = cantidad_disponible; }
}


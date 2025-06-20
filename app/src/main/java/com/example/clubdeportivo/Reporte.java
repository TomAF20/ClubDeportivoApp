package com.example.clubdeportivo;

public class Reporte {
    private String tipo;
    private String detalle;
    private String estado;

    public Reporte(String tipo, String detalle, String estado) {
        this.tipo = tipo;
        this.detalle = detalle;
        this.estado = estado;
    }

    public String getTipo() { return tipo; }
    public String getDetalle() { return detalle; }
    public String getEstado() { return estado; }
}

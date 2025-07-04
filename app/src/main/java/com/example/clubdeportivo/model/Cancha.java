package com.example.clubdeportivo.model;

import java.util.List;

public class Cancha {
    private Long id;
    private String disponibilidad;
    private String estado;
    private double ancho;
    private double largo;
    private String arbitroNombre;
    private List<String> horariosDisponibles;

    // Getters y setters
    public Long getId() { return id; }
    public String getDisponibilidad() { return disponibilidad; }
    public String getEstado() { return estado; }
    public double getAncho() { return ancho; }
    public double getLargo() { return largo; }
    public String getArbitroNombre() { return arbitroNombre; }
    public List<String> getHorariosDisponibles() { return horariosDisponibles; }
}

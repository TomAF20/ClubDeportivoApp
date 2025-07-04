package com.example.clubdeportivo.model;

public class Incidencia {
    private Long idUsuario;
    private String tipo;
    private String comentario;
    private String prioridad;

    public Incidencia(Long idUsuario, String tipo, String comentario, String prioridad) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.comentario = comentario;
        this.prioridad = prioridad;
    }

    // Getters y setters si los necesitas
}
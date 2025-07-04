package com.example.clubdeportivo.model;

import java.util.List;

public class Evento {
    private Long id;
    private String nombreEvento;
    private String descripcion;
    private String fechaLanzamiento;
    private String fechaRealizacion;
    private String nombreOrganizador;
    private List<PremioDTO> premios;
    private String imagenEvento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(String fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }

    public String getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(String fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public String getNombreOrganizador() { return nombreOrganizador; }
    public void setNombreOrganizador(String nombreOrganizador) { this.nombreOrganizador = nombreOrganizador; }

    public List<PremioDTO> getPremios() { return premios; }
    public void setPremios(List<PremioDTO> premios) { this.premios = premios; }

    public String getImagenEvento() { return imagenEvento; }
    public void setImagenEvento(String imagenEvento) { this.imagenEvento = imagenEvento; }
}

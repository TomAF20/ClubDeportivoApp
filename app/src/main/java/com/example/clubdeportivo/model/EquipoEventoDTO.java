package com.example.clubdeportivo.model;

import java.util.List;

public class EquipoEventoDTO {

    private String nombreEquipo;
    private Long idEvento;
    private Long idSocio;
    private List<JugadorDTO> jugadores;

    public EquipoEventoDTO() {}

    public EquipoEventoDTO(String nombreEquipo, Long idEvento, Long idSocio, List<JugadorDTO> jugadores) {
        this.nombreEquipo = nombreEquipo;
        this.idEvento = idEvento;
        this.idSocio = idSocio;
        this.jugadores = jugadores;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }
}

package com.example.clubdeportivo.model;

public class JugadorDTO {

    private String dni;

    public JugadorDTO() {}

    public JugadorDTO(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}

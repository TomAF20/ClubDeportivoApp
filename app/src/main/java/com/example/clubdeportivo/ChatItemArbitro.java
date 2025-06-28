package com.example.clubdeportivo;

public class ChatItemArbitro {
    private String nombre;
    private String ultimoMensaje;

    public ChatItemArbitro(String nombre, String ultimoMensaje) {
        this.nombre = nombre;
        this.ultimoMensaje = ultimoMensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }
}

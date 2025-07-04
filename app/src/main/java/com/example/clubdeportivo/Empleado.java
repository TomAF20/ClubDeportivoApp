package com.example.clubdeportivo;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String dni;
    private String cargo;
    private String correo;
    private String contrasena;

    private boolean expandido;

    public Empleado(String nombre, String apellidos, String dni,
                    String cargo, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cargo = cargo;
        this.correo = correo;
        this.contrasena = contrasena;
        this.expandido = false;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getCargo() { return cargo; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }

    public boolean isExpandido() { return expandido; }
    public void setExpandido(boolean expandido) { this.expandido = expandido; }
}


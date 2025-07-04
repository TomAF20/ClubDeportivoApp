package com.example.clubdeportivo;

public class RegistroMedico {
    private String nombre;
    private int edad;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;

    public RegistroMedico() {}

    public RegistroMedico(String nombre, int edad, String sintomas, String diagnostico, String tratamiento) {
        this.nombre = nombre;
        this.edad = edad;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;

    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getSintomas() {
        return sintomas;
    }
    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

}

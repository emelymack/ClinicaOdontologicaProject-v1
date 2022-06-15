package com.dh.clase23.dominio;

public class Odontologo {
    private int id;
    private String apellido;
    private String nombre;
    private String matricula;

    public Odontologo(){}

    public Odontologo(String apellido, String nombre, String matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Odontologo(int id, String apellido, String nombre, String matricula) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo{" + '\n' +
                "id=" + id + '\n' +
                ", apellido='" + apellido + '\n' +
                ", nombre='" + nombre + '\n' +
                ", matricula='" + matricula + '\n' +
                '}';
    }
}

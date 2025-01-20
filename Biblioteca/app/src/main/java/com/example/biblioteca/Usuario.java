package com.example.biblioteca;

public class Usuario {
    private String nombre;
    private String clave;

    public Usuario(String nombre, String clave){
        this.nombre=nombre;
        this.clave=clave;
    }

    public String getNombre(){return this.nombre;}
    public String getClave(){return this.clave;}
}

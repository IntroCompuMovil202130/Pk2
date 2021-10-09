package com.example.pk2.model;

public class Motel {
    String nombre;
    String id;
    String direccion;
    String imagen;

    public Motel(String nombre, String id, String direccion, String imagen) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public Motel() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

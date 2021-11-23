package com.example.pk2.model;

public class Mensaje_Texto {
    String contenidoMensaje;
    Usuario dueno;
    long createdAt;
    public Mensaje_Texto(){}

    public Mensaje_Texto(String contenidoMensaje, Usuario dueno, long createdAt) {
        this.contenidoMensaje = contenidoMensaje;
        this.dueno = dueno;
        this.createdAt = createdAt;
    }

    public String getContenidoMensaje() {
        return contenidoMensaje;
    }

    public void setContenidoMensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }

    public Usuario getDueno() {
        return dueno;
    }

    public void setDueno(Usuario dueno) {
        this.dueno = dueno;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}

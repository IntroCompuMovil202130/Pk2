package com.example.pk2.model;

public class Usuario {
    String correo, ccontraseña,nombre,apellido,cedula, id;
    double lat;
    double lon;
    boolean ubi;

    public Usuario() {

    }

    public Usuario(String correo, String ccontraseña, String nombre, String apellido, String cedula, String id, double lat, double lon, boolean ubi) {
        this.correo = correo;
        this.ccontraseña = ccontraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.ubi = ubi;
    }

    public boolean isUbi() {
        return ubi;
    }

    public void setUbi(boolean ubi) {
        this.ubi = ubi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCcontraseña() {
        return ccontraseña;
    }

    public void setCcontraseña(String ccontraseña) {
        this.ccontraseña = ccontraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}

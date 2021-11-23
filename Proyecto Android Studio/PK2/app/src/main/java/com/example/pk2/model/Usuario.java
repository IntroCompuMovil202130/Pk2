package com.example.pk2.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String correo, ccontraseña,nombre,apellido,cedula, id;
    double lat;
    double lon;
    boolean ubi;
    List<Chat> listChats;

    public Usuario() {
        listChats = new ArrayList<>();
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
        listChats = new ArrayList<>();
    }
    public void agregarChat(Chat myChat)
    {
        if (listChats == null)
        {
            listChats = new ArrayList<>();
        }
        listChats.add(myChat);
    }

    public boolean doesChatExists(String idChat)
    {
        if (listChats == null)
        {
            return false;
        }
        for (Chat e : listChats)
        {
            if (e.getId_Reciever() == idChat)
            {
                return true;
            }
        }
        return false;
    }

    public void agregarMensaje(Mensaje_Texto mensaje, String idChat)
    {
        for (Chat e : listChats)
        {
            if (e.getId_Reciever() == idChat)
            {
                e.agregarMensaje(mensaje);
            }
        }
    }
    public List<Mensaje_Texto> getMessageList(String idChat)
    {
        List<Mensaje_Texto> emptyList = new ArrayList<>();
        if (listChats == null)
        {
            return emptyList;
        }
        for (Chat e : listChats)
        {
            if (e.getId_Reciever() == idChat)
            {
                return e.getNoMensajes();
            }
        }
        return emptyList;
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

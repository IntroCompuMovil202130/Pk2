package com.example.pk2.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    List<Mensaje_Texto> noMensajes;
    String id_Reciever;

    public Chat(String id_Reciever) {
        this.id_Reciever = id_Reciever;
        this.noMensajes = new ArrayList<>();
    }
    public Chat()
    {
        noMensajes = new ArrayList<>();
    }

    public void agregarMensaje(Mensaje_Texto mensaje)
    {
        if (noMensajes == null)
        {
            noMensajes = new ArrayList<>();
        }
        noMensajes.add(mensaje);
    }

    public List<Mensaje_Texto> getNoMensajes() {
        return noMensajes;
    }

    public String getId_Reciever() {
        return id_Reciever;
    }

    public void setId_Reciever(String id_Reciever) {
        this.id_Reciever = id_Reciever;
    }
}

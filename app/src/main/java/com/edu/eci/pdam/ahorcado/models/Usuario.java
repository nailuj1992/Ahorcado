package com.edu.eci.pdam.ahorcado.models;

import java.io.Serializable;

/**
 * Created by Julian Gonzalez Prieto (Avuuna la Luz del Alba) on 3/2/17.
 */

public class Usuario implements Serializable {
    private String nombre;
    private int puntaje;

    public Usuario(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}

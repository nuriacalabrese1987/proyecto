package com.gmq.proyectogmq.model;

public class Centros {
    private int n_centro;
    private String nombre;
    private double latitud;
    private double longitud;
    private String direccion;

    public int getN_centro() {
        return n_centro;
    }

    public void setN_centro(int n_centro) {
        this.n_centro = n_centro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

package com.gmq.proyectogmq.model;

import java.io.Serializable;

public class Empleados implements Serializable {


    private Long id_empleado;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private int n_departamento;
    private int n_centro;
    private String url_storage;
    private int token;


    public Long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getN_departamento() {
        return n_departamento;
    }

    public void setN_departamento(int n_departamento) {
        this.n_departamento = n_departamento;
    }

    public int getN_centro() {
        return n_centro;
    }

    public void setN_centro(int n_centro) {
        this.n_centro = n_centro;
    }

    public String getUrl_storage() {
        return url_storage;
    }

    public void setUrl_storage(String url_storage) {
        this.url_storage = url_storage;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public Empleados(Long id_empleado, String nombre, String apellidos, String direccion, String telefono, int n_departamento, int n_centro, String url_storage, int token) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.n_departamento = n_departamento;
        this.n_centro = n_centro;
        this.url_storage = url_storage;
        this.token = token;
    }

    public Empleados() {
    }
}

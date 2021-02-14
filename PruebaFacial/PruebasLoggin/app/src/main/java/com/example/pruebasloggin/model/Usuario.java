package com.example.pruebasloggin.model;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
    Long n_fichaje;

    int id_empleado;

    Date fecha;

    boolean estado;

    public Long getN_fichaje() {
        return n_fichaje;
    }

    public void setN_fichaje(Long n_fichaje) {
        this.n_fichaje = n_fichaje;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

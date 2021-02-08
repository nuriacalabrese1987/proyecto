package com.gmq.proyectogmq.model;

import java.util.Date;

public class Nominas {
    private int n_nomina;
    private int id_empelado;
    private String url;
    private Date fecha;

    public int getN_nomina() {
        return n_nomina;
    }

    public void setN_nomina(int n_nomina) {
        this.n_nomina = n_nomina;
    }

    public int getId_empelado() {
        return id_empelado;
    }

    public void setId_empelado(int id_empelado) {
        this.id_empelado = id_empelado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

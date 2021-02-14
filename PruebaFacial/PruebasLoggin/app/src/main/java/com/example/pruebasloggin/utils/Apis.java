package com.example.pruebasloggin.utils;

public class Apis {

    public static String URL_01_LOGGIN = "http://192.168.1.53:1635/fichajes/";

    public static String URL_NOMINA = "http://192.168.1.53:1635/nominas/";

    public static UsuarioService getUsuario () {
        return UsuarioCliente.getEmpleado(URL_01_LOGGIN).create(UsuarioService.class);
    }

    public static UsuarioService getNomina() {
        return UsuarioCliente.getEmpleado(URL_NOMINA).create(UsuarioService.class);
    }
}

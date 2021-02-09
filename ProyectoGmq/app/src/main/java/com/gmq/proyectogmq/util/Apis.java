package com.gmq.proyectogmq.util;

public class Apis {
    public static String URL_EMPLEADOS = "http://192.168.1.53:1635/empleados/";

    public static String URL_NOMINAS="";

    public static String URL_CENTROS="";

    public static String URL_DEPARTAMENTOS="";

    public static String URL_FICHAJES="";

    public static final String TABLA_EMPLEADO="empleado";
    public static final String CREATE_TABLA_EMPLEADO="create table "+TABLA_EMPLEADO+"(id_empleado int primary key, nombre varchar(30), apellidos varchar(30), direccion varchar(100), telefono int, n_departamento int, " +
            "n_centro int, url_storage varchar(200), token int)";
}

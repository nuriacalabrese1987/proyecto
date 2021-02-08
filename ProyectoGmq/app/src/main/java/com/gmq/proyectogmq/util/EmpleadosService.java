package com.gmq.proyectogmq.util;

import com.gmq.proyectogmq.model.Empleados;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmpleadosService {
    /*
    Añadir peticiones GET O POST PERTINENTES
     */
    @GET("token/{telefono}")
    Call<List<Empleados>> getEmpleado (@Path("telefono") String telefono);
}

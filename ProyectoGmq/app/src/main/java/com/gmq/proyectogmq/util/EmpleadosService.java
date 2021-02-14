package com.gmq.proyectogmq.util;

import com.gmq.proyectogmq.model.Empleados;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmpleadosService {
    /*
    Añadir peticiones GET O POST PERTINENTES
     */
    @GET("solicitar/{telefono}")
    Call<List<Empleados>> getEmpleado (@Path("telefono") String telefono);

    @POST("token/{telefono}")
    Call<Boolean> getToken (@Path("telefono") String telefono);

    @POST("login/{telefono}")
    Call<Boolean> getLogin (@Path("telefono") String telefono, @Body String token);
}

package com.gmq.proyectogmq.util;

import com.gmq.proyectogmq.model.Nominas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NominasService {
    /*
    Metodo para ver las nominas del empleado
     */

    @GET("verNominas/{id}")
    Call<List<Nominas>> getNominas(@Path("id") int id);

}

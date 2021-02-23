package com.gmq.proyectogmq.util;

import com.gmq.proyectogmq.model.Centros;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FichajesService {
        /*
        Metodo POST que envia la imagen, latitud y longitud al back para realizar el fichaje
     */


    @POST("hacerFichaje/{telefono}/{distancia}")
    Call<Boolean> hacerFichaje(@Path("telefono") String telefono,
                               @Path("distancia") float distancia,
                               @Body String imagen);

    @GET("hacerFichaje/{centro}")
    Call<Centros> solicitarCentro(@Path("centro") String centro);

}

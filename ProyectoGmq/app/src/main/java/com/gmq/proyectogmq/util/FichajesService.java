package com.gmq.proyectogmq.util;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FichajesService {
        /*
        Metodo POST que envia la imagen, latitud y longitud al back para realizar el fichaje
     */


    @POST("hacerFichaje/{telefono}/{latitud}/{longitud")
    Call<Boolean> hacerFichaje(@Path("telefono") String telefono,
                               @Path("latitud") String latitud,
                               @Path("longitud") String longitud,
                               @Body String imagen);

}

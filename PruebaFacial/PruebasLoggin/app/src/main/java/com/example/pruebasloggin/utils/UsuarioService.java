package com.example.pruebasloggin.utils;

import com.example.pruebasloggin.model.Usuario;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    //Metodo GET que devuelva el nombre del usuario y los datos al pasarle loggin

    @POST("hacerFichaje/{telefono}")
    Call<Boolean> getEmpleado(@Path("telefono") String telefono, @Body String imagen);

    @GET("descargarNomina/12")
    Call<ResponseBody> descargarNomina();
}

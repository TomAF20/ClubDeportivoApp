package com.example.clubdeportivo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("productos")
    Call<Producto> guardarProducto(@Body Producto producto);

    @GET("productos")
    Call<List<Producto>> obtenerProductos();

    @POST("bajas")
    Call<Void> darDeBaja(@Body BajaProducto bajaProducto);

    @POST("registro-medico")
    Call<ResponseBody> guardarRegistroMedico(@Body RegistroMedico registroMedico);

    @GET("usuarios")
    Call<List<Usuario>> obtenerUsuarios();

    @GET("usuarios")
    Call<List<Usuario>> obtenerUsuariosPorRol(@Query("rol") String rol);

    @GET("ingresos")
    Call<List<Ingreso>> getIngresos();


}

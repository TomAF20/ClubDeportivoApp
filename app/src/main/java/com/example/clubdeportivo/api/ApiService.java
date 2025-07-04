package com.example.clubdeportivo.api;

import com.example.clubdeportivo.model.EquipoEventoDTO;
import com.example.clubdeportivo.model.Incidencia;
import com.example.clubdeportivo.model.LoginRequest;
import com.example.clubdeportivo.model.Cancha;
import com.example.clubdeportivo.model.ReservaCanchaRequest;
import com.example.clubdeportivo.model.Usuario;
import com.example.clubdeportivo.model.Producto;
import com.example.clubdeportivo.model.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    @POST("/api/usuario/login")
    Call<Usuario> login(@Body LoginRequest request);
    @GET("/api/usuario/{nombreUsuar io}")
    Call<Usuario> getUsuario(@Path("nombreUsuario") String nombreUsuario);
    @POST("/api/incidencias/crear")
    Call<Void> enviarIncidencia(@Body Incidencia request);
    @GET("api/canchas")
    Call<List<Cancha>> getCanchas();
    @GET("/api/canchas/{id}")
    Call<Cancha> getCanchaById(@Path("id") Long id);
    @GET("/api/usuario/validar-dni/{dni}")
    Call<Boolean> validarDniSocio(@Path("dni") String dni);
    @GET("/api/productos/disponibles")
    Call<List<Producto>> obtenerProductos();
    @POST("/api/reservas/crear")
    Call<Void> crearReserva(@Body ReservaCanchaRequest request);
    @GET("/api/eventos")
    Call<List<Evento>> getEventos();
    @GET("/api/eventos/{id}")
    Call<Evento> obtenerEventoPorId(@Path("id") Long id);
    @POST("/api/equipos-evento/registrar")
    Call<Void> registrarEquipo(@Body EquipoEventoDTO dto);

}

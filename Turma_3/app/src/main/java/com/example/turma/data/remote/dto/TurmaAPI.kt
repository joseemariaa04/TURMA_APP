package com.example.turma.data.remote.dto

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TurmaAPI {
    @GET("proyectos")
    suspend fun recuperaProyectos():List<ProyectoDTO>

    @GET("proyectos/{id}")
    suspend fun recuperaProyectosPorId(@Path("id")id:String): ProyectoDTO

    @POST("proyectos")
    suspend fun insertaProyecto(@Body nuevo: ProyectoDTO):ProyectoDTO

    @DELETE("proyectos/{id}")
    suspend fun borraProyecto(@Path("id") id:String):ProyectoDTO

    @PUT("proyectos/{id}")
    suspend fun modificaProyecto(@Body nuevo: ProyectoDTO,@Path("id")id:String):ProyectoDTO

    @GET("proyectos/{id}/tareas")
    suspend fun obtenerTareasProyecto(@Path("id")id:String): List<TareaDTO>

    @POST("tareas")
    suspend fun insertaTarea(@Body nuevo:TareaDTO):TareaDTO

    @PUT("tareas/{id}")
    suspend fun modificaTarea(@Body nuevo: TareaDTO,@Path("id")id:String): TareaDTO
}
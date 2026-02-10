package com.example.turma.data.repositorio

import com.example.turma.data.Tarea
import com.example.turma.data.remote.dto.RetrofitProvider
import com.example.turma.data.remote.dto.TareaDTO
import com.example.turma.data.remote.dto.toOriginal
import com.example.turma.data.toDTO
import com.example.turma.ui.theme.provider

object RepositorioTareas {

    suspend fun obtenerTareas(id: Int): List<Tarea> {
        val listaDTO = RetrofitProvider.api.obtenerTareasProyecto(id.toString())
        return listaDTO.map { toOriginal(it) }
    }

    suspend fun insertaTarea(idProy:Int,nombre:String):Tarea{
        val tarea = RetrofitProvider.api.insertaTarea(TareaDTO(proyectoId = idProy.toString(), nombre = nombre))
        return toOriginal(tarea)
    }

    suspend fun modificaTarea(nueva: Tarea, id: Int,idProy: Int):Tarea{
        val tarea = RetrofitProvider.api.modificaTarea(toDTO(nueva,idProy.toString()),id.toString())
        return toOriginal(tarea)
    }
}
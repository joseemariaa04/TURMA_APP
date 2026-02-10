package com.example.turma.data.remote.dto

import com.example.turma.data.Tarea

data class TareaDTO(
    val id:String?=null,
    val proyectoId:String,
    val nombre:String,
    val completada: Boolean = false
)

fun toOriginal(t:TareaDTO): Tarea {
    return Tarea(
        id= t.id?.toInt()?:-1,//Si es null, le asigna -1
        nombre = t.nombre,
        completada = t.completada
    )
}



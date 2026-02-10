package com.example.turma.data

import com.example.turma.data.remote.dto.TareaDTO

data class Tarea(
    val id:Int?=null,//He añadido ese campo porque si no no encontraba forma de hacerles put a las tareas al marcarlas como completadas porque desconocía su id
    val nombre: String,
    val completada: Boolean = false
)

fun toDTO(t:Tarea,idProy:String):TareaDTO{
    return TareaDTO(
        id=t.id.toString(),
        nombre = t.nombre,
        completada = t.completada,
        proyectoId = idProy
    )
}

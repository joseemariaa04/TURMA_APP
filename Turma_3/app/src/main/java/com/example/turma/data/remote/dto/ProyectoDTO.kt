package com.example.turma.data.remote.dto

import com.example.turma.data.Proyecto
import com.example.turma.data.SituacionProyecto

data class ProyectoDTO(
    val id:String="",
    val area:String,
    val objetivo:String,
    val situacion:String = SituacionProyecto.INICIADO.toString(),
    val tareas:List<TareaDTO>? =emptyList()
)

fun toOriginal(p:ProyectoDTO): Proyecto {
    return Proyecto(
        id= p.id.toInt(),
        area = p.area,
        objetivo = p.objetivo,
        situacion = when (p.situacion) {//Esto convierte los Strings que recibimos de mockapi a Situaciones del enum
            "0", "pendiente" -> SituacionProyecto.PENDIENTE
            "1", "situacion 1", "iniciado" -> SituacionProyecto.INICIADO
            "2", "situacion 2", "finalizado" -> SituacionProyecto.FINALIZADO
            else -> SituacionProyecto.PENDIENTE
        },
        tareas = p.tareas?.map { toOriginal(it) } ?: emptyList()//He puesto esto porque si mockapi devolvia una lista nula pegaba un petardazo
    )
}

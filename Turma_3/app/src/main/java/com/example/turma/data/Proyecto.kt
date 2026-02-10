package com.example.turma.data

import com.example.turma.data.remote.dto.ProyectoDTO

data class Proyecto(
    val id: Int,
    val area: String,
    val objetivo: String,
    val situacion: SituacionProyecto = SituacionProyecto.PENDIENTE,
    val tareas: List<Tarea> = emptyList()
)
fun toDTO(p:Proyecto): ProyectoDTO {//Transforma un objeto proyecto en DTO
    return ProyectoDTO(
        id=p.id.toString(),
        area = p.area,
        objetivo = p.objetivo,
        situacion = p.situacion.toString(),
        tareas = p.tareas.map { toDTO(it,p.id.toString()) }
    )
}

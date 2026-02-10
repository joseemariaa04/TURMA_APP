package com.example.turma.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turma.data.Proyecto
import com.example.turma.data.SituacionProyecto
import com.example.turma.data.Tarea
import com.example.turma.data.repositorio.RepositorioProyectos
import com.example.turma.data.repositorio.RepositorioTareas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProyectosViewModel : ViewModel() {
    private val _proyectos = MutableStateFlow<List<Proyecto>>(emptyList())
    val proyectos: StateFlow<List<Proyecto>> = _proyectos
    var cargando: Boolean=false//Para mostrar el circulo de carga

    init {
        cargarProyectos()
    }

    fun crearProyecto(area: String, objetivo: String){
        viewModelScope.launch {
            val nuevo = RepositorioProyectos.insertaProyecto(Proyecto(0, area, objetivo))//El id aqui es irrelevante, mockapi le asigna uno
            _proyectos.update { lista -> lista + nuevo }
        }
    }

    fun actualizarProyecto(idProyecto: Int, area: String, objetivo: String){
        viewModelScope.launch {
            val proyectoActualizado = RepositorioProyectos.modificaProyecto(
                Proyecto(
                    id = idProyecto,
                    area = area,
                    objetivo = objetivo
                )
            )

            _proyectos.update { lista ->
                lista.map { p ->
                    if (p.id == idProyecto) proyectoActualizado else p
                }
            }
        }


    }

    private fun cargarProyectos() {//Es algo lento, pero sino no cargaba el estado de los proyectos hasta que entrara en uno de ellos
        viewModelScope.launch {
            cargando=true
            val proyectos = RepositorioProyectos.recuperaProyectos()
            val proyectosActualizados = proyectos.map { proyecto ->
                val tareas =
                    try{
                        RepositorioTareas.obtenerTareas(proyecto.id)
                    }catch (e: Exception){//Si ese proyecto no tiene tareas, pega un petardazo
                        e.printStackTrace()
                        emptyList()
                    }
                proyecto.copy(
                    tareas = tareas,
                    situacion = calcularSituacion(tareas)
                )
            }
            _proyectos.value = proyectosActualizados
            cargando=false
        }
    }


    fun obtenerTareasProyecto(idProyecto: Int) {
        viewModelScope.launch {
            val tareas = try{
                RepositorioTareas.obtenerTareas(idProyecto)
            }catch (e: Exception){//Lo mismo, si no tiene tareas lanza un 404
                e.printStackTrace()
                emptyList()
            }
            _proyectos.update { lista ->
                lista.map { proyecto ->
                    if (proyecto.id == idProyecto) {
                        proyecto.copy(
                            tareas = tareas,
                            situacion = calcularSituacion(tareas)
                        )
                    } else {
                        proyecto
                    }
                }
            }
        }
    }




    fun eliminarProyecto(idProyecto: Int) {
        viewModelScope.launch {
            RepositorioProyectos.borraProyecto(idProyecto)
            _proyectos.update { lista -> lista.filterNot { it.id == idProyecto } }
        }

    }

    fun obtenerProyecto(idProyecto: Int): Proyecto? {
        return _proyectos.value.firstOrNull { it.id == idProyecto }
    }


    fun añadirTarea(idProyecto: Int, nombreTarea: String) {
        val nombreOk = nombreTarea.trim()
        if (nombreOk.isEmpty()) return
        viewModelScope.launch {
            val dto=RepositorioTareas.insertaTarea(idProyecto, nombreTarea)

            _proyectos.update { lista ->
                lista.map { p ->
                    if (p.id != idProyecto) return@map p

                    val tareasNuevas = p.tareas + Tarea(id=dto.id,nombre = nombreOk, completada = false)
                    p.copy(
                        tareas = tareasNuevas,
                        situacion = calcularSituacion(tareasNuevas)
                    )
                }
            }
        }
    }

    fun cambiarTareaCompletada(
        idProyecto: Int, indiceTarea: Int, completada: Boolean,
        idTarea: Int?,
        nombreTarea: String
    ) {
        viewModelScope.launch {
            RepositorioTareas.modificaTarea(Tarea(id = idTarea, nombre = nombreTarea, completada = completada), id = idTarea!!, idProy = idProyecto)
            _proyectos.update { lista ->
                lista.map { p ->
                    if (p.id != idProyecto) return@map p
                    if (indiceTarea !in p.tareas.indices) return@map p

                    val tareasNuevas = p.tareas.mapIndexed { i, t ->
                        if (i == indiceTarea) t.copy(completada = completada) else t
                    }

                    p.copy(
                        tareas = tareasNuevas,
                        situacion = calcularSituacion(tareasNuevas)
                    )
                }
            }
        }
    }


    //////////////////   AUXILIAR   ////////////

    private fun calcularSituacion(tareas: List<Tarea>): SituacionProyecto {
        if (tareas.isEmpty()) return SituacionProyecto.PENDIENTE
        val completadas = tareas.count { it.completada }
        return when {
            completadas == 0 -> SituacionProyecto.PENDIENTE
            completadas == tareas.size -> SituacionProyecto.FINALIZADO
            else -> SituacionProyecto.INICIADO
        }
    }
}

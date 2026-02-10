package com.example.turma.data.repositorio

import com.example.turma.data.Proyecto
import com.example.turma.data.SituacionProyecto
import com.example.turma.data.Tarea
import com.example.turma.data.remote.dto.ProyectoDTO
import com.example.turma.data.remote.dto.RetrofitProvider
import com.example.turma.data.remote.dto.toOriginal
import com.example.turma.data.toDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update



// El Repository adapta los datos de la API al modelo de la aplicación para no tener que
// cambiar la app entera, es unico que se me ha ocurrido no se si estara bien
object RepositorioProyectos {

    suspend fun recuperaProyectos(): List<Proyecto> {
        val listaDTO = RetrofitProvider.api.recuperaProyectos()
        return listaDTO.map { toOriginal(it) }
    }

    suspend fun recuperaProyectoPorId(id: Int): Proyecto {
        val dto = RetrofitProvider.api.recuperaProyectosPorId(id.toString())
        return toOriginal(dto)
    }

    suspend fun insertaProyecto(p: Proyecto): Proyecto {
        val dto = toDTO(p)
        val creado = RetrofitProvider.api.insertaProyecto(dto)
        return toOriginal(creado)
    }

    suspend fun borraProyecto(id: Int): Proyecto {
        val dto = RetrofitProvider.api.borraProyecto(id.toString())
        return toOriginal(dto)
    }

    suspend fun modificaProyecto(p: Proyecto): Proyecto {
        val dto = toDTO(p)
        val modificado = RetrofitProvider.api.modificaProyecto(dto, p.id.toString())
        return toOriginal(modificado)
    }

}


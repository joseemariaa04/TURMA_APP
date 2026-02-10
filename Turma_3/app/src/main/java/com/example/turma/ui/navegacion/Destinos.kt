package com.example.turma.ui.navegacion

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object DestinoListaProyectos

@Serializable
data class DestinoDetProyInfo(
    val idProyecto: Int
)

@Serializable
data class DestinoDetProyTareas(
    val idProyecto: Int
)


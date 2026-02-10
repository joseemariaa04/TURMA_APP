package com.example.turma.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.turma.ui.navegacion.DestinoDetProyInfo
import com.example.turma.ui.navegacion.DestinoDetProyTareas
import com.example.turma.ui.navegacion.DestinoListaProyectos
import com.example.turma.ui.navegacion.Home


@Composable
fun BarraNavegacionInferior(
    destinoActual: NavDestination?,
    alIrHome: () -> Unit,
    alIrProyectos: () -> Unit
) {
    val seleccionadoHome = destinoActual?.hasRoute(Home::class) == true

    val seleccionadoProyectos =
        destinoActual?.hasRoute(DestinoListaProyectos::class) == true ||
                destinoActual?.hasRoute(DestinoDetProyInfo::class) == true ||
                destinoActual?.hasRoute(DestinoDetProyTareas::class) == true

    NavigationBar {
        NavigationBarItem(
            selected = seleccionadoHome,
            onClick = alIrHome,
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = seleccionadoProyectos,
            onClick = alIrProyectos,
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Proyectos") },
            label = { Text("Proyectos") }
        )
    }
}
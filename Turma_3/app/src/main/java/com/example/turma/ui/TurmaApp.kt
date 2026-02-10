package com.example.turma.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.turma.ui.componentes.BarraNavegacionInferior
import com.example.turma.ui.navegacion.DestinoDetProyInfo
import com.example.turma.ui.navegacion.DestinoDetProyTareas
import com.example.turma.ui.navegacion.DestinoListaProyectos
import com.example.turma.ui.navegacion.Home
import com.example.turma.ui.pantallas.PantallaDetProyInfo
import com.example.turma.ui.pantallas.PantallaDetProyTareas
import com.example.turma.ui.pantallas.PantallaHome
import com.example.turma.ui.pantallas.PantallaListaProyectos
import com.example.turma.ui.vm.ProyectosViewModel


@Composable
fun TurmaApp() {
    val navController = rememberNavController()
    // Saber en qué ruta estamos
    val entradaBackStackActual by navController.currentBackStackEntryAsState()
    val destinoActual = entradaBackStackActual?.destination

    val esPantallaRaiz =
        destinoActual?.hasRoute(Home::class) == true ||
        destinoActual?.hasRoute(DestinoListaProyectos::class) == true

    val vmProyectos: ProyectosViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(
                destinoActual = destinoActual,
                alIrHome = {navController.navigate(Home)},
                alIrProyectos = {navController.navigate(DestinoListaProyectos)}
            )
        }
    ) { innerPadding ->
        GrafoNavegacion(
            navController=navController,
            vm=vmProyectos,
            modifier=Modifier.padding(innerPadding)
        )
        ManejadorBack(enabled=esPantallaRaiz)// Doble back para salir
    }

}

@Composable
fun GrafoNavegacion(
    navController: NavHostController,
    vm: ProyectosViewModel,
    modifier: Modifier =Modifier
){
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            PantallaHome()
        }
        composable<DestinoListaProyectos> {
            PantallaListaProyectos(
                alCrearProyecto = {
                    navController.navigate(DestinoDetProyInfo(idProyecto = -1))
                },
                alEditarProyecto = { id ->
                    navController.navigate(DestinoDetProyInfo(idProyecto = id))
                },
                vm=vm
            )
        }
        composable<DestinoDetProyInfo> { backStackEntry ->
            val args = backStackEntry.toRoute<DestinoDetProyInfo>()
            PantallaDetProyInfo(
                idProyecto = args.idProyecto,
                alGuardar = {id->
                    navController.navigate(DestinoDetProyTareas(idProyecto = id))
                },
                vm = vm
            )
        }
        composable<DestinoDetProyTareas> { backStackEntry ->
            val args = backStackEntry.toRoute<DestinoDetProyTareas>()
            PantallaDetProyTareas(
                idProyecto = args.idProyecto,
                vm = vm
            )
        }
    }
}

@Composable
private fun ManejadorBack(enabled: Boolean) {
    val actividad = LocalActivity.current
    var ultimo by remember { mutableStateOf(0L) }
    var tostada= remember{Toast.makeText(actividad, "Pulsa otra vez para salir", Toast.LENGTH_SHORT)}
    BackHandler(
        enabled = enabled
    ) {
        val ahora = System.currentTimeMillis()
        if (ahora - ultimo < 500) {
            tostada.cancel()
            actividad?.finish()
        } else {
            ultimo = ahora
            tostada.show()
        }
    }
}
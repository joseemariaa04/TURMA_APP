package com.example.turma.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.turma.data.Proyecto
import com.example.turma.data.SituacionProyecto
import com.example.turma.ui.navegacion.DestinoDetProyInfo
import com.example.turma.ui.vm.ProyectosViewModel

@Composable
fun PantallaListaProyectos(
    alCrearProyecto: () -> Unit,
    alEditarProyecto: (Int) -> Unit,
    vm: ProyectosViewModel
) {

    val proyectos by vm.proyectos.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    alCrearProyecto()
                }
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    ) { innerPadding ->
        if(vm.cargando){
            CircularProgressIndicator(
                Modifier.fillMaxWidth()
            )
        }
        Listado(
            modifier = Modifier.padding(innerPadding),
            lista = proyectos,
            alDeslizar = { idProyecto ->
                vm.eliminarProyecto(idProyecto)
            },
            alPulsar = { id -> alEditarProyecto(id) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Listado(
    modifier: Modifier = Modifier,
    lista: List<Proyecto>,
    alDeslizar: (Int) -> Unit,
    alPulsar: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        items(
            items = lista,
            key = { it.id }
        ) { proyecto ->
            val estadoSwipe = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    if (value == SwipeToDismissBoxValue.EndToStart) {
                        alDeslizar(proyecto.id)
                    }
                    true
                }
            )
            SwipeToDismissBox(
                state = estadoSwipe,
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true,
                backgroundContent = {},
            ) {
                TarjetaProyecto(
                    proyecto = proyecto,
                    alPulsar = { alPulsar(proyecto.id) }
                )
            }
        }
    }
}

@Composable
fun TarjetaProyecto(
    proyecto: Proyecto,
    alPulsar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = alPulsar
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(0, 50, 50, 0))
                    .weight(1.0f)
                    .background(
                        when (proyecto.situacion) {
                            SituacionProyecto.INICIADO -> MaterialTheme.colorScheme.secondary
                            SituacionProyecto.PENDIENTE -> MaterialTheme.colorScheme.tertiary
                            SituacionProyecto.FINALIZADO -> MaterialTheme.colorScheme.primary
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = proyecto.situacion.aspecto,
                    style = MaterialTheme.typography.displayMedium,
                )
            }
            Column(
                Modifier
                    .weight(3.0f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "${proyecto.id} - ${proyecto.area}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = proyecto.objetivo,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

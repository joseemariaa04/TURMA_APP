package com.example.turma.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.turma.R
import com.example.turma.ui.vm.ProyectosViewModel

@Composable
fun PantallaDetProyTareas(
    idProyecto: Int,
    vm: ProyectosViewModel,
    modifier: Modifier = Modifier
) {
    //val proyecto = vm.obtenerProyecto(idProyecto)
    val mostrarDialogo = remember { mutableStateOf(false) }
    val nombreNuevaTarea = remember { mutableStateOf("") }
    val proyectos by vm.proyectos.collectAsStateWithLifecycle()
    val proyecto = proyectos.firstOrNull { it.id == idProyecto }
    val tareas = proyecto?.tareas ?: emptyList()

    LaunchedEffect(idProyecto) {
        if(idProyecto!=-1){
            vm.obtenerTareasProyecto(idProyecto)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                nombreNuevaTarea.value = ""
                mostrarDialogo.value = true
            }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "${stringResource(id = R.string.etiqueta_id)} $idProyecto",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "${stringResource(id = R.string.campo_area)}: ${proyecto?.area ?: ""}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${stringResource(id = R.string.campo_objetivo)}: ${proyecto?.objetivo ?: ""}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.detalle_tareas_titulo),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(12.dp))


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(tareas) { indice, tarea ->
                    FilaTarea(
                        nombre = tarea.nombre,
                        completada = tarea.completada,
                        alCambiar = { completadaNueva ->
                            vm.cambiarTareaCompletada(
                                idProyecto = idProyecto,
                                indiceTarea = indice,
                                idTarea = tarea.id,//Ahora paso el id de la tarea para poder hacerle el put
                                completada = completadaNueva,
                                nombreTarea = tarea.nombre
                            )
                        }
                    )
                }
            }
        }
    }

    if (mostrarDialogo.value) {
        DialogoNuevaTarea(
            nombre = nombreNuevaTarea.value,
            alCambiarNombre = { nombreNuevaTarea.value = it },
            onDismiss = { mostrarDialogo.value = false },
            onConfirm = {
                val nombreOk = nombreNuevaTarea.value.trim()
                if (nombreOk.isNotEmpty()) {
                    vm.añadirTarea(idProyecto = idProyecto, nombreTarea = nombreOk)
                }
                mostrarDialogo.value = false
            }
        )
    }
}

@Composable
fun FilaTarea(
    nombre: String,
    completada: Boolean,
    alCambiar: (Boolean) -> Unit
) {
    Row(
        modifier= Modifier.fillMaxWidth(),
        verticalAlignment= Alignment.CenterVertically
    ) {
        Text(
            text= nombre,
            modifier= Modifier.weight(1f),
            style= MaterialTheme.typography.bodyLarge
        )
        Checkbox(
            checked= completada,
            onCheckedChange= alCambiar
        )
    }
}

@Composable
fun DialogoNuevaTarea(
    nombre: String,
    alCambiarNombre: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest=onDismiss,
        title = { Text(text = stringResource(id= R.string.dialogo_nueva_tarea_titulo)) },
        text = {
            OutlinedTextField(
                value = nombre,
                onValueChange = alCambiarNombre,
                placeholder = { Text(text = stringResource(id = R.string.dialogo_nueva_tarea_pista)) },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton={
            TextButton(onClick= onConfirm) {
                Text(text =stringResource(id= R.string.accion_añadir))
            }
        },
        dismissButton={
            TextButton(onClick=onDismiss) {
                Text(text=stringResource(id= R.string.accion_cancelar))
            }
        }
    )
}

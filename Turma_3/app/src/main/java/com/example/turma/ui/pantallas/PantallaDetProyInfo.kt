package com.example.turma.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turma.R
import com.example.turma.ui.vm.ProyectosViewModel
import kotlinx.coroutines.delay

@Composable
fun PantallaDetProyInfo(
    idProyecto:Int,
    alGuardar: (Int) -> Unit,
    vm: ProyectosViewModel,
    modifier: Modifier = Modifier
) {
    var area by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }

    val esEdicion = idProyecto != -1

    val contexto = LocalContext.current

    // Precarga en modo edición (solo cuando cambia el id)
    LaunchedEffect(idProyecto) {
        if (esEdicion) {
            val proyecto = vm.obtenerProyecto(idProyecto)
            if (proyecto != null) {
                area = proyecto.area
                objetivo = proyecto.objetivo
            }
        } else {
            area = ""
            objetivo = ""
        }
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (esEdicion) "Editar proyecto" else "Nuevo proyecto",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = if (esEdicion) {
                "${stringResource(id = R.string.etiqueta_id)} $idProyecto"
            } else {
                "${stringResource(id = R.string.etiqueta_id)} (se asignará al guardar)"
            },
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = area,
            onValueChange = { area = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Área") }
        )
        OutlinedTextField(
            value = objetivo,
            onValueChange = { objetivo = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Objetivo") }
        )

        Button(
            onClick = {
                val areaOk = area.trim()
                val objetivoOk = objetivo.trim()

                if (esEdicion) {
                    vm.actualizarProyecto(idProyecto, areaOk, objetivoOk)
                    alGuardar(idProyecto)//Aqui si navega sin problemas
                } else {
                    vm.crearProyecto(areaOk, objetivoOk)
                    Toast.makeText(contexto,"Proyecto añadido correctamente", Toast.LENGTH_SHORT).show()//En su lugar muestro un mensaje
                    //No navega porque me daba nullPointerException, porque el vm no tenia el nuevo antes de pasar a la siguiente pantalla
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar y continuar")
        }
    }
    }



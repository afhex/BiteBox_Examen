package com.bitebox.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bitebox.app.viewmodel.BiteBoxViewModel

/**
 * Pantalla 3: Detalle del Platillo.
 * Muestra la imagen grande, nombre, descripción y precio del platillo seleccionado.
 * Permite agregar el platillo al carrito y volver automáticamente.
 *
 * @param platilloId ID del platillo a mostrar.
 * @param viewModel ViewModel compartido para obtener datos y agregar al carrito.
 * @param onVolver Callback para volver a la pantalla anterior.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    platilloId: Int,
    viewModel: BiteBoxViewModel,
    onVolver: () -> Unit
) {
    // Buscar el platillo por ID desde el ViewModel
    val platillo = viewModel.obtenerPlatilloPorId(platilloId)

    // Si no se encuentra el platillo, mostrar mensaje de error
    if (platillo == null) {
        Text(
            text = "Platillo no encontrado",
            modifier = Modifier.padding(16.dp)
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = platillo.nombre) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen grande del platillo
            AsyncImage(
                model = platillo.urlImagen,
                contentDescription = platillo.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            // Contenido de texto
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = platillo.nombre,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = platillo.categoria,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = platillo.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "$${String.format("%.2f", platillo.precio)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón para añadir al carrito
                Button(
                    onClick = {
                        viewModel.agregarAlCarrito(platillo)
                        onVolver()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir al Carrito")
                }
            }
        }
    }
}

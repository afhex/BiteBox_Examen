package com.bitebox.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bitebox.app.ui.components.FilterSection
import com.bitebox.app.ui.components.PlatilloCard
import com.bitebox.app.viewmodel.BiteBoxViewModel

/**
 * Pantalla 2: Catálogo del Menú.
 * Muestra un saludo personalizado, filtros de categoría y una lista de platillos.
 * Incluye un FAB para acceder al carrito.
 *
 * @param nombre Nombre del usuario recibido desde la pantalla de login.
 * @param viewModel ViewModel compartido con la lógica de negocio.
 * @param onPlatilloClick Callback al tocar un platillo, recibe el ID.
 * @param onCarritoClick Callback al presionar el FAB del carrito.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    nombre: String,
    viewModel: BiteBoxViewModel,
    onPlatilloClick: (Int) -> Unit,
    onCarritoClick: () -> Unit
) {
    // Estado local para la categoría seleccionada en los filtros
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    // Obtener platillos filtrados desde el ViewModel
    val platillosFiltrados = viewModel.filtrarPorCategoria(categoriaSeleccionada)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Hola, $nombre")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCarritoClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Ir al carrito"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Sección de filtros
            item {
                FilterSection(
                    categoriaSeleccionada = categoriaSeleccionada,
                    onCategoriaSeleccionada = { categoriaSeleccionada = it }
                )
            }

            // Lista de platillos
            items(platillosFiltrados, key = { it.id }) { platillo ->
                PlatilloCard(
                    platillo = platillo,
                    onClick = { onPlatilloClick(platillo.id) }
                )
            }
        }
    }
}

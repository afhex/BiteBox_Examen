package com.bitebox.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Componente reutilizable que muestra una fila de filtros por categoría.
 * Permite al usuario filtrar los platillos entre "Todos", "Pizza" y "Hamburguesa".
 *
 * @param categoriaSeleccionada La categoría actualmente seleccionada.
 * @param onCategoriaSeleccionada Callback que se ejecuta al seleccionar una categoría.
 */
@Composable
fun FilterSection(
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    val categorias = listOf("Todos", "Pizza", "Hamburguesa")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categorias.forEach { categoria ->
            FilterChip(
                selected = categoriaSeleccionada == categoria,
                onClick = { onCategoriaSeleccionada(categoria) },
                label = { Text(text = categoria) }
            )
        }
    }
}

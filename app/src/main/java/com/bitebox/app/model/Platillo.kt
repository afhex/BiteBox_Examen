package com.bitebox.app.model

/**
 * Modelo de datos que representa un platillo del menú.
 * Contiene toda la información necesaria para mostrar y gestionar cada producto.
 */
data class Platillo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val urlImagen: String
)

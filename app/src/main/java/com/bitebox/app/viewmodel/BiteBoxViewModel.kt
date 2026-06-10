package com.bitebox.app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bitebox.app.model.Platillo

/**
 * ViewModel principal de BiteBox.
 * Contiene la lógica de negocio: catálogo de platillos, gestión del carrito
 * y cálculo del total. Es compartido entre todas las pantallas.
 */
class BiteBoxViewModel : ViewModel() {

    // ── Catálogo de platillos (datos hardcoded) ──────────────────────────

    val platillos: List<Platillo> = listOf(
        // Pizzas
        Platillo(
            id = 1,
            nombre = "Pizza Margarita",
            descripcion = "Pizza clásica con salsa de tomate natural, mozzarella fresca derretida y hojas de albahaca. Una receta tradicional italiana que nunca falla, con masa artesanal horneada a la perfección.",
            precio = 8.99,
            categoria = "Pizza",
            urlImagen = "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=600"
        ),
        Platillo(
            id = 2,
            nombre = "Pizza Pepperoni",
            descripcion = "Pizza con generosas rodajas de pepperoni crujiente sobre una cama de queso mozzarella derretido y salsa de tomate casera. El clásico favorito de todos los tiempos.",
            precio = 10.99,
            categoria = "Pizza",
            urlImagen = "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=600"
        ),
        Platillo(
            id = 3,
            nombre = "Pizza BBQ Chicken",
            descripcion = "Pizza con pollo a la barbacoa desmenuzado, cebolla morada caramelizada, cilantro fresco y queso gouda ahumado. Una combinación perfecta de sabores dulces y ahumados.",
            precio = 12.49,
            categoria = "Pizza",
            urlImagen = "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=600"
        ),
        // Hamburguesas
        Platillo(
            id = 4,
            nombre = "Hamburguesa Clásica",
            descripcion = "Hamburguesa de carne de res a la parrilla con lechuga fresca, tomate, cebolla, pepinillos y nuestra salsa especial de la casa. Servida en pan brioche tostado.",
            precio = 7.99,
            categoria = "Hamburguesa",
            urlImagen = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600"
        ),
        Platillo(
            id = 5,
            nombre = "Hamburguesa Doble Queso",
            descripcion = "Doble carne de res jugosa con doble queso cheddar derretido, pepinillos encurtidos y salsa mostaza-miel. Para los que quieren el doble de sabor en cada mordida.",
            precio = 9.99,
            categoria = "Hamburguesa",
            urlImagen = "https://images.unsplash.com/photo-1553979459-d2229ba7433b?w=600"
        ),
        Platillo(
            id = 6,
            nombre = "Hamburguesa BBQ Bacon",
            descripcion = "Hamburguesa premium con tocino crujiente ahumado, aros de cebolla dorados, queso pepper jack y salsa BBQ artesanal. La favorita de los amantes del sabor intenso.",
            precio = 11.49,
            categoria = "Hamburguesa",
            urlImagen = "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?w=600"
        )
    )

    // ── Carrito de compras (lista reactiva observable) ───────────────────

    private val _carrito = mutableStateListOf<Platillo>()
    val carrito: List<Platillo> get() = _carrito

    // ── Funciones de negocio ────────────────────────────────────────────

    /** Agrega un platillo al carrito de compras. */
    fun agregarAlCarrito(platillo: Platillo) {
        _carrito.add(platillo)
    }

    /** Vacía completamente el carrito de compras. */
    fun vaciarCarrito() {
        _carrito.clear()
    }

    /** Calcula el total a pagar sumando los precios de todos los productos en el carrito. */
    fun calcularTotal(): Double {
        return _carrito.sumOf { it.precio }
    }

    /** Busca un platillo por su ID. Retorna null si no se encuentra. */
    fun obtenerPlatilloPorId(id: Int): Platillo? {
        return platillos.find { it.id == id }
    }

    /** Filtra los platillos por categoría. Si la categoría es "Todos", retorna la lista completa. */
    fun filtrarPorCategoria(categoria: String): List<Platillo> {
        return if (categoria == "Todos") {
            platillos
        } else {
            platillos.filter { it.categoria == categoria }
        }
    }
}

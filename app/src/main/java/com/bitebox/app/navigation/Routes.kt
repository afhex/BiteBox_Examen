package com.bitebox.app.navigation

/**
 * Objeto que centraliza todas las rutas de navegación de la aplicación.
 * Cada pantalla tiene su ruta definida aquí para evitar strings sueltos en el código.
 */
object Routes {
    const val LOGIN = "login"
    const val MENU = "menu/{nombre}"
    const val DETAIL = "detail/{platilloId}"
    const val CART = "cart"

    // Funciones auxiliares para construir rutas con parámetros
    fun menuConNombre(nombre: String) = "menu/$nombre"
    fun detailConId(platilloId: Int) = "detail/$platilloId"
}

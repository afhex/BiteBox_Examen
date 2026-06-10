package com.bitebox.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bitebox.app.ui.screens.CartScreen
import com.bitebox.app.ui.screens.DetailScreen
import com.bitebox.app.ui.screens.LoginScreen
import com.bitebox.app.ui.screens.MenuScreen
import com.bitebox.app.viewmodel.BiteBoxViewModel

/**
 * Composable principal de navegación.
 * Define el NavHost con las 4 pantallas y comparte un único ViewModel entre todas.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // ViewModel compartido entre todas las pantallas
    val viewModel: BiteBoxViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        // Pantalla 1: Login / Bienvenida
        composable(Routes.LOGIN) {
            LoginScreen(
                onEntrar = { nombre ->
                    navController.navigate(Routes.menuConNombre(nombre))
                }
            )
        }

        // Pantalla 2: Catálogo del Menú
        composable(
            route = Routes.MENU,
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            MenuScreen(
                nombre = nombre,
                viewModel = viewModel,
                onPlatilloClick = { platilloId ->
                    navController.navigate(Routes.detailConId(platilloId))
                },
                onCarritoClick = {
                    navController.navigate(Routes.CART)
                }
            )
        }

        // Pantalla 3: Detalle del Platillo
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("platilloId") { type = NavType.IntType })
        ) { backStackEntry ->
            val platilloId = backStackEntry.arguments?.getInt("platilloId") ?: 0
            DetailScreen(
                platilloId = platilloId,
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla 4: Carrito / Checkout
        composable(Routes.CART) {
            CartScreen(
                viewModel = viewModel,
                onConfirmar = {
                    viewModel.vaciarCarrito()
                    navController.popBackStack()
                }
            )
        }
    }
}

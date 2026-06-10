package com.bitebox.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bitebox.app.navigation.AppNavigation
import com.bitebox.app.ui.theme.BiteBoxTheme

/**
 * Activity principal de BiteBox.
 * Configura el tema y delega toda la navegación a AppNavigation.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BiteBoxTheme {
                AppNavigation()
            }
        }
    }
}

package com.bitebox.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// ── Esquema de colores claro ────────────────────────────────────────────

private val LightColorScheme = lightColorScheme(
    primary = Orange700,
    onPrimary = White,
    primaryContainer = Orange100,
    onPrimaryContainer = Gray900,
    secondary = Gray600,
    onSecondary = White,
    background = Gray50,
    onBackground = Gray900,
    surface = White,
    onSurface = Gray900,
    surfaceVariant = Gray100,
    onSurfaceVariant = Gray800,
    error = ErrorRed,
    onError = White
)

// ── Tema principal de la aplicación ─────────────────────────────────────

@Composable
fun BiteBoxTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = BiteBoxTypography,
        content = content
    )
}

package com.example.filmix_project.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val FilmixColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = TextPrimary,
    secondary = Accent,
    onSecondary = TextPrimary,
    background = Background,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline = DividerColor,
    outlineVariant = DividerColor
)

@Composable
fun Filmix_projectTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FilmixColorScheme,
        typography = Typography,
        content = content
    )
}

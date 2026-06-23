package com.passby.malloc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Emerald,
    onPrimary = Color.White,
    secondary = Graphite,
    tertiary = WarmAmber,
    background = LightBackground,
    surface = LightSurface,
)

private val DarkColors = darkColorScheme(
    primary = MintEmerald,
    onPrimary = Color(0xFF003829),
    secondary = Color(0xFFD1D5DB),
    tertiary = Color(0xFFFBBF24),
    background = DarkBackground,
    surface = DarkSurface,
)

@Composable
fun PassByTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = PassByTypography,
        content = content,
    )
}


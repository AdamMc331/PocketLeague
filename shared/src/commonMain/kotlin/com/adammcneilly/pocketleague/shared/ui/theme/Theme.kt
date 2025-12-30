package com.adammcneilly.pocketleague.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun PocketLeagueTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val pocketLeagueColors = when {
        darkTheme -> darkPocketLeagueColors
        else -> lightPocketLeagueColors
    }

    CompositionLocalProvider(LocalPocketLeagueColors provides pocketLeagueColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}

object PocketLeagueTheme {
    val colors: PocketLeagueColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPocketLeagueColors.current
}

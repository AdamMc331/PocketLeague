package com.adammcneilly.pocketleague.core.ui

import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A wrapper around [Divider] that provides the color based on the material 3 theme.
 */
@Composable
fun Material3Divider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Divider(
        modifier = modifier,
        color = color.copy(alpha = DIVIDER_ALPHA),
    )
}

private const val DIVIDER_ALPHA = 0.12F

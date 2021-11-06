package com.adammcneilly.pocketleague.core.ui

import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A wrapper around [Divider] that provides the color based on the material 3 theme.
 */
@Composable
fun Material3Divider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12F),
    )
}

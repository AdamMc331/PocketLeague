package com.adammcneilly.pocketleague.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A custom implementation of a divider that uses the Material 3 theme and also applies a
 * vertical orientation to the divider.
 */
@Composable
fun Material3VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    thickness: Dp = 1.dp,
) {
    Box(
        modifier
            .fillMaxHeight()
            .width(thickness)
            .background(color = color.copy(alpha = DIVIDER_ALPHA))
    )
}

private const val DIVIDER_ALPHA = 0.12F

package com.adammcneilly.pocketleague.feature.debugmenu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Renders the debug menu.
 */
@Composable
internal fun DebugMenuContent(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Debug Menu Screen",
        modifier = modifier,
    )
}

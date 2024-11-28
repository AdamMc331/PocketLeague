package com.adammcneilly.pocketleague.shared.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Main composable entrypoint to the shared multiplatform version of
 * the pocket league app.
 */
@Composable
fun PocketLeagueApp(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Entry Point",
        modifier = modifier,
    )
}

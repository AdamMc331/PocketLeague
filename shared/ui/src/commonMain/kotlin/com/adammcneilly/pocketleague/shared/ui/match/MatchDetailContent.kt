package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Compose content for the match detail screen.
 */
@Composable
fun MatchDetailContent(
    matchId: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Match Detail For: $matchId",
        modifier = modifier,
    )
}

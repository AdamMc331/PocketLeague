package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * UI content for the team detail screen with the given [state].
 */
@Composable
internal fun TeamDetailContent(
    state: TeamDetailScreen.State,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Team Detail Screen, Team: ${state.team.name}",
        modifier = modifier,
    )
}

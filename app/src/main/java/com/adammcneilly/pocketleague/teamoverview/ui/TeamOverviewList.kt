package com.adammcneilly.pocketleague.teamoverview.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.ui.Material3Divider

/**
 * Consumes a list of [teams] and displays their overview information.
 */
@Composable
fun TeamOverviewList(
    teams: List<TeamOverviewDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(teams) { team ->
            ToggleableTeamOverviewListItem(
                team = team,
            )

            Material3Divider()
        }
    }
}

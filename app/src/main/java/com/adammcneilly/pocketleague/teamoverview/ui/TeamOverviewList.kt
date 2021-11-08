package com.adammcneilly.pocketleague.teamoverview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.ui.Material3Divider

@Composable
fun TeamOverviewList(
    teams: List<TeamOverviewDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        teams.forEach { team ->
            TeamOverviewListItem(
                team = team,
            )

            Material3Divider()
        }
    }
}

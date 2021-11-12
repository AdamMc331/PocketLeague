package com.adammcneilly.pocketleague.teamoverview.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.ui.Material3Divider

@Composable
fun TeamOverviewList(
    teams: List<TeamOverviewDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(teams) { team ->
            TeamOverviewListItem(
                team = team,
            )

            Material3Divider()
        }
    }
}

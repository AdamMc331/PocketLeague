package com.adammcneilly.pocketleague.teamoverview.ui

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * A wrapper around a [TeamOverviewListItem] which maintains state
 * about whether or not to show the roster list.
 */
@Composable
fun ToggleableTeamOverviewListItem(
    team: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    val showRosterList = remember {
        mutableStateOf(false)
    }

    TeamOverviewListItem(
        team = team,
        showRosterList = showRosterList.value,
        modifier = modifier
            .clickable {
                showRosterList.value = !showRosterList.value
            },
    )
}

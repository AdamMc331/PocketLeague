package com.adammcneilly.pocketleague.teamlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewList

@Composable
fun TeamListContent(
    viewState: TeamListViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        if (viewState.showContent) {
            TeamOverviewList(teams = viewState.teams)
        }
    }
}

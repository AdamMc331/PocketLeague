package com.adammcneilly.pocketleague.teamlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewList

/**
 * Renders the actual content of the team list screen given the [viewState].
 */
@Composable
fun TeamListContent(
    viewState: TeamListViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (viewState.showContent) {
            TeamOverviewList(teams = viewState.teams)
        }

        if (viewState.showLoading) {
            CenteredMaterial3CircularProgressIndicator()
        }
    }
}

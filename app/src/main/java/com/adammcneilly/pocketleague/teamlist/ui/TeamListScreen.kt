package com.adammcneilly.pocketleague.teamlist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * The containing screen to show a list of teams. All UI information
 * is powered by the supplied [viewModel].
 */
@Composable
fun TeamListScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    TeamListContent(
        viewState = viewState.value,
        modifier = modifier,
    )
}

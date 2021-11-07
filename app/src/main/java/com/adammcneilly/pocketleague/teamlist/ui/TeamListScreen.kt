package com.adammcneilly.pocketleague.teamlist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TeamListScreen(
    viewModel: TeamListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    TeamListContent(
        viewState = viewState.value,
    )
}

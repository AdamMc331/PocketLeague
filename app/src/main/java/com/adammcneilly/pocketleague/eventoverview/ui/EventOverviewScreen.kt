package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Displays the [EventOverviewContent] with the view state pulled from the supplied [viewModel].
 */
@Composable
fun EventOverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: EventOverviewViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    EventOverviewContent(
        viewState = viewState.value,
        modifier = modifier,
    )
}

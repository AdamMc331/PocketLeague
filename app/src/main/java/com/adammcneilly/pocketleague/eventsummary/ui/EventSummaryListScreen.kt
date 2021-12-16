package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * The screen composable for [EventSummaryListContent] that manages state from the given [viewModel].
 */
@Composable
fun EventSummaryListScreen(
    modifier: Modifier = Modifier,
    viewModel: EventSummaryListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    EventSummaryListContent(
        viewState = viewState.value,
        modifier = modifier
            .fillMaxSize(),
    )
}

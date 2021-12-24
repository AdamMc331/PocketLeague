package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.EventOverviewScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * The screen composable for [EventSummaryListContent] that manages state from the given [viewModel].
 */
@Destination(start = true)
@Composable
fun EventSummaryListScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: EventSummaryListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(viewState.value) {
        val selectedEvent = (viewState.value as? EventSummaryListViewState.Success)?.selectedEvent

        if (selectedEvent != null) {
            navigator.navigate(
                EventOverviewScreenDestination(
                    eventId = selectedEvent.id,
                )
            )

            viewModel.navigatedToEventOverview()
        }
    }

    EventSummaryListContent(
        viewState = viewState.value,
        modifier = modifier
            .fillMaxSize(),
    )
}

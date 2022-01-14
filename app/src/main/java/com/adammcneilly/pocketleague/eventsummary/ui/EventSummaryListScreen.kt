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
        val selectedEventId = viewState.value.selectedEventId

        if (selectedEventId != null) {
            navigator.navigate(
                EventOverviewScreenDestination(
                    eventId = selectedEventId,
                )
            )

            viewModel.navigatedToEventOverview()
        }
    }

    EventSummaryListContent(
        viewState = viewState.value,
        eventClicked = viewModel::eventClicked,
        modifier = modifier
            .fillMaxSize(),
    )
}

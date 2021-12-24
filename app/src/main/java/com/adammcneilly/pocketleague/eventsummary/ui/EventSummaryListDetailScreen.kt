package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewContent
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewViewModel
import com.ramcosta.composedestinations.annotation.Destination

/**
 * A [Composable] screen that combines state from the [eventSummaryViewModel] as well as the
 * [eventOverviewViewModel].
 */
@Destination
@Composable
fun EventSummaryListDetailScreen(
    modifier: Modifier = Modifier,
    eventSummaryViewModel: EventSummaryListViewModel = hiltViewModel(),
    eventOverviewViewModel: EventOverviewViewModel = hiltViewModel(),
) {
    val eventSummaryListViewState = eventSummaryViewModel.viewState.collectAsState()
    val eventOverviewViewState = eventOverviewViewModel.viewState.collectAsState()

    LaunchedEffect(eventSummaryListViewState.value) {
        val selectedEvent = (eventSummaryListViewState.value as? EventSummaryListViewState.Success)?.selectedEvent

        if (selectedEvent != null) {
            eventOverviewViewModel.eventSelected(selectedEvent.id)
        }
    }

    Row(
        modifier = modifier
            .fillMaxSize(),
    ) {
        EventSummaryListContent(
            viewState = eventSummaryListViewState.value,
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
        )

        EventOverviewContent(
            viewState = eventOverviewViewState.value,
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
        )
    }
}

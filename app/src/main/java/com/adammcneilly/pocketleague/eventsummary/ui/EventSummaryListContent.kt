package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.core.ui.getValue

/**
 * Displays the content of an event summary list based on the supplied [viewState].
 */
@Composable
fun EventSummaryListContent(
    viewState: EventSummaryListViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        when (viewState) {
            EventSummaryListViewState.Loading -> {
                CenteredMaterial3CircularProgressIndicator()
            }
            is EventSummaryListViewState.Success -> {
                EventSummaryList(
                    displayModels = viewState.events,
                )
            }
            is EventSummaryListViewState.Error -> {
                Text(
                    text = viewState.errorMessage.getValue(),
                )
            }
        }
    }
}

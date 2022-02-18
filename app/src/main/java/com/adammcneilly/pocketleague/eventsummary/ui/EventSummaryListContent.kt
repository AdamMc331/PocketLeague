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
    viewState: com.adammcneilly.pocketleague.eventsummary.EventSummaryListViewState,
    eventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        if (viewState.showLoading) {
            CenteredMaterial3CircularProgressIndicator()
        }

        if (viewState.events.isNotEmpty()) {
            EventSummaryList(
                displayModels = viewState.events,
                eventClicked = eventClicked,
            )
        }

        val errorMessage = viewState.errorMessage

        if (errorMessage != null) {
            Text(
                text = errorMessage.getValue(),
            )
        }
    }
}

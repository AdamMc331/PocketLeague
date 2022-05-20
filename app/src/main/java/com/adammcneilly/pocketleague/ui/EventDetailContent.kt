package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailViewState

/**
 * Renders the [viewState] of detailed event information.
 */
@Composable
fun EventDetailContent(
    viewState: EventDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(text = "Event Detail For: ${viewState.eventId}")
    }
}

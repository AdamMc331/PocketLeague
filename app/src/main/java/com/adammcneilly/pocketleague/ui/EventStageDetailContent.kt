package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailViewState

/**
 * Renders the [viewState] for the event stage detail screen.
 */
@Composable
fun EventStageDetailContent(
    viewState: EventStageDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {

        Text(
            text = "Event Stage Detail"
        )

        if (viewState.showLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
            )
        }
    }
}

package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewState

/**
 * Displays event overview information.
 */
@Composable
fun EventOverviewScreen(
    state: EventOverviewState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "This is the event overview screen: ${state.eventId}.",
    )
}

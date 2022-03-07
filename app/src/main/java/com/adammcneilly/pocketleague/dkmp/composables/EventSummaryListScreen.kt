package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.eventsummarylist.EventSummaryListState

/**
 * Displays a list of event summaries.
 */
@Composable
fun EventSummaryListScreen(
    state: EventSummaryListState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "This is the event summary screen.",
    )
}

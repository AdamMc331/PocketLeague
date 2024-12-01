package com.adammcneilly.pocketleague.shared.app.ui.event

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.app.ui.components.ListItemDividerCard

/**
 * A card component that shows a list of event summaries.
 */
@Composable
fun EventSummaryListCard(
    events: List<EventSummaryDisplayModel>,
    onEventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItemDividerCard(
        items = events,
        modifier = modifier,
    ) { event ->
        EventSummaryListItem(
            event = event,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .clickable {
                    onEventClicked.invoke(event.eventId)
                },
        )
    }
}

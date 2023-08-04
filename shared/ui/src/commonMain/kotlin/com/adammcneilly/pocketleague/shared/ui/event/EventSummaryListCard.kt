package com.adammcneilly.pocketleague.shared.ui.event

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.shared.ui.components.ListItemDividerCard

/**
 * A card component that shows a list of event summaries.
 */
@Composable
fun EventSummaryListCard(
    events: List<EventSummaryDisplayModel>,
    onEventClicked: (Event.Id) -> Unit,
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

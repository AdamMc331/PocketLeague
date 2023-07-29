package com.adammcneilly.pocketleague.shared.ui.event

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event

/**
 * A card component that shows a list of event summaries.
 */
@Composable
fun EventSummaryListCard(
    events: List<EventSummaryDisplayModel>,
    onEventClicked: (Event.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        events.forEachIndexed { index, event ->
            // We need to propagate the container color over to our list item,
            // otherwise the list item will set its own background.
            EventSummaryListItem(
                event = event,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .clickable {
                        onEventClicked.invoke(event.eventId)
                    },
            )

            if (index != events.lastIndex) {
                Divider()
            }
        }
    }
}

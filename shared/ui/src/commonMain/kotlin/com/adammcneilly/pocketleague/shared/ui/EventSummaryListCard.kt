package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel

/**
 * A card component that shows a list of event summaries.
 */
@Composable
@Suppress("MagicNumber")
fun EventSummaryListCard(
    events: List<EventSummaryDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        events.forEachIndexed { index, event ->
            // We need to propagate the container color over to our list item,
            // otherwise the list item will set its own background.
            EventSummaryListItem(
                event = event,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            if (index != events.lastIndex) {
                Divider()
            }
        }
    }
}

package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A card component that shows a list of event summaries.
 */
@Composable
@Suppress("MagicNumber")
fun EventSummaryListCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        for (i in 0 until 5) {
            // We need to propogate the container color over to our list item,
            // otherwise the list item will set its own background.
            EventSummaryListItem(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            if (i != 4) {
                Divider()
            }
        }
    }
}

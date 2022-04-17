package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.models.Event

/**
 * Show a list of [events] inside of a lazy column.
 */
@Composable
fun EventList(
    events: List<Event>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(events) { index, event ->
            EventListItem(event = event)

            if (index != events.lastIndex) {
                Divider()
            }
        }
    }
}

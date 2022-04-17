package com.adammcneilly.pocketleague.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.models.Event

/**
 * Used to show a specific [event] inside of a list.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventListItem(
    event: Event,
) {
    val startDateString = event.startDate?.let { startDate ->
        DateTimeFormatter().formatLocalDateTime(
            localDateTime = startDate,
            formatPattern = "MMM dd, yyyy"
        )
    }

    ListItem(
        text = {
            Text(
                text = event.name,
            )
        },
        overlineText = startDateString?.let {
            @Composable {
                Text(text = startDateString)
            }
        },
    )
}

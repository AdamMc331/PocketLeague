package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
    ListItem(
        text = {
            Text(
                text = event.name,
            )
        },
        overlineText = {
            val startDateString = event.startDate?.let { startDate ->
                DateTimeFormatter().formatLocalDateTime(
                    localDateTime = startDate,
                    formatPattern = "MMM dd, yyyy",
                )
            }

            Text(text = startDateString.orEmpty())
        },
        icon = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Event Image",
                modifier = Modifier.size(48.dp),
            )
        },
    )
}

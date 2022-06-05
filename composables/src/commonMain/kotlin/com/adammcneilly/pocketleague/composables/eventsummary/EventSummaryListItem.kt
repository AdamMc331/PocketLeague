package com.adammcneilly.pocketleague.composables.eventsummary

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import io.ktor.http.Url

/**
 * Used to show a specific [displayModel] inside of a list.
 *
 * @param[displayModel] The event summary to render.
 * @param[modifier] Optional modifications to apply to the root of this composable.
 */
@Composable
fun EventSummaryListItem(
    displayModel: EventSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EventImage(displayModel)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            EventDates(displayModel)

            EventNames(displayModel)
        }
    }
}

@Composable
private fun EventNames(displayModel: EventSummaryDisplayModel) {
    Text(
        text = displayModel.name,
    )
}

@Composable
private fun EventDates(displayModel: EventSummaryDisplayModel) {
    Text(
        text = displayModel.dateString,
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
private fun EventImage(displayModel: EventSummaryDisplayModel) {
    val imageUrl = if (isSystemInDarkTheme()) {
        displayModel.darkThemeImageUrl
    } else {
        displayModel.lightThemeImageUrl
    }.orEmpty()

    Box(
        modifier = Modifier
            .size(48.dp),
    ) {
        if (imageUrl.isNotEmpty()) {
            KamelImage(
                resource = lazyPainterResource(
                    data = Url(imageUrl),
                ),
                contentDescription = "Event Image",
                modifier = Modifier
                    .size(48.dp),
                crossfade = true,
                onLoading = {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = CircleShape,
                            ),
                    )
                },
            )
        }
    }
}

package com.adammcneilly.pocketleague.composables.eventsummary

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel

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
        modifier = Modifier
            .defaultMinSize(minWidth = 150.dp)
//            .placeholder(
//                visible = displayModel.name.isEmpty(),
//                shape = CircleShape,
//                color = MaterialTheme.colorScheme.inverseSurface,
//            )
    )
}

@Composable
private fun EventDates(displayModel: EventSummaryDisplayModel) {
    Text(
        text = displayModel.dateString,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .defaultMinSize(minWidth = 50.dp)
//            .placeholder(
//                visible = displayModel.startDate.isEmpty(),
//                shape = CircleShape,
//                color = MaterialTheme.colorScheme.inverseSurface,
//            )
    )
}

@Composable
private fun EventImage(displayModel: EventSummaryDisplayModel) {
    val imageUrl = if (isSystemInDarkTheme()) {
        displayModel.darkThemeImageUrl
    } else {
        displayModel.lightThemeImageUrl
    }

//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(imageUrl)
//            .crossfade(true)
//            .build(),
//        contentDescription = "Event Image",
//        modifier = Modifier
//            .size(48.dp)
//            .darkThemeBackgroundModifier(
//                color = MaterialTheme.colorScheme.inverseSurface,
//                shape = CircleShape,
//            )
//            .padding(8.dp)
//            .placeholder(
//                visible = imageUrl == null,
//                shape = CircleShape,
//                color = MaterialTheme.colorScheme.inverseSurface,
//            ),
//    )
}

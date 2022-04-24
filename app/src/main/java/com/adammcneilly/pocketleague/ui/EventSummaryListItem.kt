package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.shared.displaymodels.EventSummaryDisplayModel
import com.google.accompanist.placeholder.material.placeholder

/**
 * Used to show a specific [event] inside of a list.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventSummaryListItem(
    displayModel: EventSummaryDisplayModel,
) {
    ListItem(
        text = {
            Text(
                text = displayModel.name,
                modifier = Modifier
                    .defaultMinSize(minWidth = 150.dp)
                    .placeholder(
                        visible = displayModel.name.isEmpty(),
                        shape = CircleShape,
                    )
            )
        },
        overlineText = {
            Text(
                text = displayModel.startDate,
            )
        },
        icon = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(displayModel.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Event Image",
                modifier = Modifier
                    .size(48.dp)
                    .placeholder(
                        visible = displayModel.imageUrl == null,
                        shape = CircleShape,
                    ),
            )
        },
    )
}

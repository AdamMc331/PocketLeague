package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.shared.displaymodels.EventSummaryDisplayModel
import com.google.accompanist.placeholder.material.placeholder

/**
 * Used to show a specific [displayModel] inside of a list.
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
                    color = MaterialTheme.colorScheme.inverseSurface,
                ),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = displayModel.startDate,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp)
                    .placeholder(
                        visible = displayModel.startDate.isEmpty(),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )

            Text(
                text = displayModel.name,
                modifier = Modifier
                    .defaultMinSize(minWidth = 150.dp)
                    .placeholder(
                        visible = displayModel.name.isEmpty(),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )
        }
    }
}

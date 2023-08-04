package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel

/**
 * A [ListItem] component to display an [eventStage].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventStageListItem(
    eventStage: EventStageSummaryDisplayModel,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
) {
    val colorsToUse = if (containerColor != Color.Unspecified) {
        ListItemDefaults.colors(
            containerColor = containerColor,
        )
    } else {
        ListItemDefaults.colors()
    }

    ListItem(
        modifier = modifier,
        colors = colorsToUse,
        headlineText = {
            Text(
                text = eventStage.name,
            )
        },
        supportingText = {
            Text(
                text = eventStage.dateString,
            )
        },
    )
}

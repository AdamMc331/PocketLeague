package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Shows high level summary information about an event.
 *
 * @param[modifier] Any optional modifications to apply to this component.
 * @param[containerColor] If supplied, use this color to specify the container color of the
 * list item, different from what is the default. This may be used in situations where a list item is shown
 * on a card component, so the color should not be the default surface.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSummaryListItem(
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
        headlineText = {
            Text(
                text = "NA Spring Open",
            )
        },
        overlineText = {
            Text(
                text = "May 05 - May 07, 2023",
            )
        },
        colors = colorsToUse,
        modifier = modifier,
    )
}

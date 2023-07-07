package com.adammcneilly.pocketleague.shared.ui.event

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

/**
 * Shows high level summary information about an event.
 *
 * @param[event] The event summary to show inside of this list item.
 * @param[modifier] Any optional modifications to apply to this component.
 * @param[containerColor] If supplied, use this color to specify the container color of the
 * list item, different from what is the default. This may be used in situations where a list item is shown
 * on a card component, so the color should not be the default surface.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSummaryListItem(
    event: EventSummaryDisplayModel,
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

    val placeholderColor = if (containerColor != Color.Unspecified) {
        PlaceholderDefaults.color(
            backgroundColor = containerColor,
        )
    } else {
        PlaceholderDefaults.color()
    }

    ListItem(
        headlineText = {
            Text(
                text = event.name,
                modifier = Modifier
                    .placeholderMaterial(
                        visible = event.isPlaceholder,
                        color = placeholderColor,
                    ),
            )
        },
        overlineText = {
            Text(
                text = event.dateRange,
                modifier = Modifier
                    .placeholderMaterial(
                        visible = event.isPlaceholder,
                        color = placeholderColor,
                    ),
            )
        },
        colors = colorsToUse,
        modifier = modifier,
    )
}

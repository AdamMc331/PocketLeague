package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Shows high level summary information about an event.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventSummaryListItem(
    modifier: Modifier = Modifier,
    colors: ListItemColors = ListItemDefaults.colors(),
) {
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
        colors = colors,
        modifier = modifier,
    )
}

package com.adammcneilly.pocketleague.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    )
}

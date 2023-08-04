package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel

/**
 * UI representation of an [event].
 */
@Composable
fun EventDetailContent(
    event: EventDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Event Detail Screen; Event name: ${event.name}",
    )
}

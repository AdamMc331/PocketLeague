package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.eventoverview.ui.EventOverviewDisplayModel

/**
 * Displays a summary of an [event] overview, if that makes sense lol. Shows the event name, date,
 * intended to appear at the top of [EventOverviewcontent].
 */
@Composable
fun EventOverviewHeader(
    event: EventOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = event.startDate,
        )

        Text(
            text = event.eventName,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

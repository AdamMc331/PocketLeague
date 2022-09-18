package com.adammcneilly.pocketleague.composables.eventstage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel

/**
 * Displays information from the supplied [displayModel] for stage summary info.
 */
@Composable
fun StageSummaryListItem(
    displayModel: EventStageSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = displayModel.name,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = displayModel.dateString,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

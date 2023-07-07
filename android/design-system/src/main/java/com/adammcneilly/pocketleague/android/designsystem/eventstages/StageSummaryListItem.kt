package com.adammcneilly.pocketleague.android.designsystem.eventstages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

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
            modifier = Modifier
                .fillMaxWidth()
                .placeholderMaterial(
                    visible = displayModel.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )

        Text(
            text = displayModel.dateString,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .defaultMinSize(minWidth = 50.dp)
                .placeholderMaterial(
                    visible = displayModel.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )
    }
}

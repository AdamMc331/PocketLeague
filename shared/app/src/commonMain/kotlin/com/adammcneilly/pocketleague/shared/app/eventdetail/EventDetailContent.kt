package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * UI representation of an [event].
 */
@Composable
fun EventDetailContent(
    event: EventDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(
            PocketLeagueTheme.sizes.listItemSpacing,
        ),
    ) {
        stagesSection(event)
    }
}

private fun LazyListScope.stagesSection(event: EventDetailDisplayModel) {
    val stages = event.getStageSummaries()

    if (stages.isEmpty()) {
        return
    }

    item {
        EventDetailSectionHeader("Stages")
    }

    item {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            stages.forEachIndexed { index, stageSummaryDisplayModel ->
                EventStageListItem(
                    eventStage = stageSummaryDisplayModel,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                if (index != stages.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun EventDetailSectionHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}

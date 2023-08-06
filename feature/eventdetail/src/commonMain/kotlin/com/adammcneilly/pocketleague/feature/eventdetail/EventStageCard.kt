package com.adammcneilly.pocketleague.feature.eventdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * A minimal [Card] component to highlight information about
 * an [eventStage].
 */
@Composable
internal fun EventStageCard(
    eventStage: EventStageSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.textSpacing),
            modifier = Modifier
                .fillMaxWidth()
                .padding(PocketLeagueTheme.sizes.cardPadding),
        ) {
            Text(
                text = eventStage.name,
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                text = eventStage.dateString,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

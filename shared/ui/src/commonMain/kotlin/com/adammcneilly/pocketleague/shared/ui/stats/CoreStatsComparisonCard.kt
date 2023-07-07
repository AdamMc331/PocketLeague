package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel

/**
 * Render a comparison of [blueTeamStats] and [orangeTeamStats] for the
 * various core stats.
 */
@Composable
fun CoreStatsComparisonCard(
    blueTeamStats: CoreStatsDisplayModel,
    orangeTeamStats: CoreStatsDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Goals",
            )

            AnimatableStatComparison(
                blueTeamValue = blueTeamStats.goals,
                orangeTeamValue = orangeTeamStats.goals,
            )

            Text(
                text = "Assists",
            )

            AnimatableStatComparison(
                blueTeamValue = blueTeamStats.assists,
                orangeTeamValue = orangeTeamStats.assists,
            )

            Text(
                text = "Saves",
            )

            AnimatableStatComparison(
                blueTeamValue = blueTeamStats.saves,
                orangeTeamValue = orangeTeamStats.saves,
            )
        }
    }
}

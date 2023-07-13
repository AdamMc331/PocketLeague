package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
            StatType.values().forEach { statType ->
                StatLine(
                    title = statType.displayName,
                    blueTeamValue = blueTeamStats.getStatsForType(statType),
                    orangeTeamValue = orangeTeamStats.getStatsForType(statType),
                )
            }
        }
    }
}

@Composable
private fun StatLine(
    title: String,
    blueTeamValue: Int,
    orangeTeamValue: Int,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
    )

    AnimatableStatComparison(
        blueTeamValue = blueTeamValue,
        orangeTeamValue = orangeTeamValue,
    )
}

/**
 * A collection of types of stats that will be displayed on a [CoreStatsComparisonCard].
 *
 * NOTE: The order of these values is the order they will be rendered on the card.
 * I don't love relying on the order of enum definitions, but it's fine for now.
 *
 * Also, in the future if we want to properly localize this, we may need a function to look
 * up the display name based on stat type.
 */
private enum class StatType(val displayName: String) {
    Score("Score"),
    Goals("Goals"),
    Assists("Assists"),
    Shots("Shots"),
    Saves("Saves"),
}

private fun CoreStatsDisplayModel.getStatsForType(statType: StatType): Int {
    return when (statType) {
        StatType.Score -> this.score
        StatType.Goals -> this.goals
        StatType.Assists -> this.assists
        StatType.Shots -> this.shots
        StatType.Saves -> this.saves
    }
}

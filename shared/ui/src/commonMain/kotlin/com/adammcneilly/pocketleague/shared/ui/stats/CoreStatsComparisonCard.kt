package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    initialAnimationPercentage: Float = 0F,
) {
    var showValues by remember {
        mutableStateOf(false)
    }

    Card(
        modifier =
            modifier
                .clickable {
                    showValues = !showValues
                }
                .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            StatType.values().forEach { statType ->
                StatLine(
                    title = statType.displayName,
                    blueTeamValue = blueTeamStats.getStatsForType(statType),
                    orangeTeamValue = orangeTeamStats.getStatsForType(statType),
                    initialAnimationPercentage = initialAnimationPercentage,
                    showValues = showValues,
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
    initialAnimationPercentage: Float,
    showValues: Boolean,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
    )

    AnimatableStatComparison(
        blueTeamValue = blueTeamValue,
        orangeTeamValue = orangeTeamValue,
        initialAnimationPercentage = initialAnimationPercentage,
        showValues = showValues,
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

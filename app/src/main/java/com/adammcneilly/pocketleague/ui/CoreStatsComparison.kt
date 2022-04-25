package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.CoreStats

/**
 * A composable to compare the [CoreStats] between two teams.
 */
@Composable
fun CoreStatsComparison(
    blueTeamStats: CoreStats,
    orangeTeamStats: CoreStats,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        StatComparisonRow(
            title = "Goals",
            blueValue = blueTeamStats.goals.toFloat(),
            orangeValue = orangeTeamStats.goals.toFloat(),
        )

        StatComparisonRow(
            title = "Saves",
            blueValue = blueTeamStats.saves.toFloat(),
            orangeValue = orangeTeamStats.saves.toFloat(),
        )

        StatComparisonRow(
            title = "Shots",
            blueValue = blueTeamStats.shots.toFloat(),
            orangeValue = orangeTeamStats.shots.toFloat(),
        )
    }
}

@Composable
private fun StatComparisonRow(
    title: String,
    blueValue: Float,
    orangeValue: Float,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
        )

        BoxWithConstraints {
            val total = blueValue + orangeValue
            val blueWeight = blueValue / total
            val orangeWeight = orangeValue / total

            val availableWidth = this.maxWidth - 2.dp
            val blueWidth = availableWidth * blueWeight
            val orangeWidth = availableWidth * orangeWeight

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(1.dp)
                        .width(blueWidth)
                )

                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .height(4.dp)
                        .width(2.dp)
                )

                Box(
                    modifier = Modifier
                        .background(color = Color.Red)
                        .height(1.dp)
                        .width(orangeWidth)
                )
            }
        }
    }
}

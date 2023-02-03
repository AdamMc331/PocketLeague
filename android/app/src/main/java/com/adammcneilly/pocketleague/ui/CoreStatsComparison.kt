package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange
import com.adammcneilly.pocketleague.core.models.CoreStats

/**
 * A composable to compare the [CoreStats] between two teams.
 */
@Composable
fun CoreStatsComparison(
    blueTeamStats: CoreStats,
    orangeTeamStats: CoreStats,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatComparisonRow(
            title = "Goals",
            blueValue = blueTeamStats.goals.toFloat(),
            orangeValue = orangeTeamStats.goals.toFloat()
        )

        StatComparisonRow(
            title = "Saves",
            blueValue = blueTeamStats.saves.toFloat(),
            orangeValue = orangeTeamStats.saves.toFloat()
        )

        StatComparisonRow(
            title = "Shots",
            blueValue = blueTeamStats.shots.toFloat(),
            orangeValue = orangeTeamStats.shots.toFloat()
        )
    }
}

@Composable
private fun StatComparisonRow(
    title: String,
    blueValue: Float,
    orangeValue: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title
        )

        BoxWithConstraints {
            val total = blueValue + orangeValue
            val blueWeight = blueValue / total
            val orangeWeight = orangeValue / total

            val statBarHeight = 4.dp
            val dividerHeight = 12.dp
            val dividerWidth = 4.dp

            val availableWidth = this.maxWidth - dividerWidth
            val blueWidth = availableWidth * blueWeight
            val orangeWidth = availableWidth * orangeWeight

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue,
                            shape = RoundedCornerShape(
                                topStartPercent = 50,
                                bottomStartPercent = 50
                            )
                        )
                        .size(
                            width = blueWidth,
                            height = statBarHeight
                        )
                )

                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .size(
                            width = dividerWidth,
                            height = dividerHeight
                        )
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange,
                            shape = RoundedCornerShape(
                                topEndPercent = 50,
                                bottomEndPercent = 50
                            )
                        )
                        .height(statBarHeight)
                        .width(orangeWidth)
                )
            }
        }
    }
}

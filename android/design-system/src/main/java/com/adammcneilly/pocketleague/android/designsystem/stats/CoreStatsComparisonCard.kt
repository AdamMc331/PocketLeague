package com.adammcneilly.pocketleague.android.designsystem.stats

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
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

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun CoreStatsComparisonCardPreview() {
    PocketLeagueTheme {
        CoreStatsComparisonCard(
            blueTeamStats = CoreStatsDisplayModel(
                score = 1000,
                goals = 7,
                assists = 4,
                saves = 3,
                shots = 1,
            ),
            orangeTeamStats = CoreStatsDisplayModel(
                score = 8729374,
                goals = 3,
                assists = 8,
                saves = 0,
                shots = 5,
            ),
            modifier = Modifier
                .padding(16.dp),
        )
    }
}

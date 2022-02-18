package com.adammcneilly.pocketleague.bracket.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

/**
 * Displays a round of a bracket UI.
 */
@Composable
fun BracketRoundList(
    bracket: BracketRoundDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = bracket.roundName,
            style = MaterialTheme.typography.headlineSmall,
        )

        bracket.series.forEach { series ->
            BracketSeriesOverviewItem(seriesOverview = series)
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
private fun BracketRoundContentPreview() {
    val seriesOverview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "G2",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "GG",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamOneWins = 4,
        teamTwoWins = 2,
    )

    val bracket = BracketRoundDisplayModel(
        roundName = "Quarter-Final",
        series = listOf(seriesOverview, seriesOverview, seriesOverview, seriesOverview),
    )

    PocketLeagueTheme {
        Surface {
            BracketRoundList(
                bracket = bracket,
            )
        }
    }
}

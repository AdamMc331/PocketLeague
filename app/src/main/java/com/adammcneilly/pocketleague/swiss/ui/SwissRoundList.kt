package com.adammcneilly.pocketleague.swiss.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

/**
 * Given a [SwissRoundDisplayModel], create a list of all series that
 * occurred during that swiss round.
 */
@Composable
fun SwissRoundList(
    swissRound: SwissRoundDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = swissRound.roundDescription,
            modifier = Modifier
                .padding(8.dp),
        )

        Material3Divider()

        swissRound.series.forEach { series ->
            SwissSeriesOverviewItem(
                seriesOverview = series,
            )

            Material3Divider()
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
private fun SwissRoundListPreview() {
    val torV1Overview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "TOR",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "V1",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamOneWins = 1,
        teamTwoWins = 3,
    )

    val fazeSSGOverview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "FAZE",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "SSG",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamOneWins = 1,
        teamTwoWins = 3,
    )

    val ghostVIBOverview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "GG",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "VIB",
            lightLogoImage = UIImage.AndroidResource(R.drawable.us),
            roster = emptyList(),
        ),
        teamOneWins = 3,
        teamTwoWins = 1,
    )

    val swissRound = SwissRoundDisplayModel(
        roundDescription = "Round 5 Matches",
        series = listOf(
            torV1Overview,
            fazeSSGOverview,
            ghostVIBOverview,
        ),
    )

    PocketLeagueTheme {
        Surface {
            SwissRoundList(
                swissRound = swissRound,
            )
        }
    }
}

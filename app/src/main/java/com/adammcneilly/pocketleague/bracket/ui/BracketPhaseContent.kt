package com.adammcneilly.pocketleague.bracket.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

/**
 * Shows a collection of bracket rounds from the supplied [phase].
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BracketPhaseContent(
    phase: BracketPhaseDisplayModel,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        count = phase.rounds.size,
        modifier = modifier,
    ) { pageIndex ->
        BracketRoundList(
            bracket = phase.rounds[pageIndex],
            modifier = Modifier
                .fillMaxSize(),
        )
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
private fun BracketPhaseContentPreview() {
    val seriesOverview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "G2",
            lightLogoImage = UIImage.Resource(R.drawable.us),
            roster = emptyList(),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "GG",
            lightLogoImage = UIImage.Resource(R.drawable.us),
            roster = emptyList(),
        ),
        teamOneWins = 4,
        teamTwoWins = 2,
    )

    val rounds = listOf(
        BracketRoundDisplayModel(
            roundName = "Quarter-Final",
            series = listOf(seriesOverview, seriesOverview, seriesOverview, seriesOverview),
        ),
        BracketRoundDisplayModel(
            roundName = "Semi-Final",
            series = listOf(seriesOverview, seriesOverview),
        ),
        BracketRoundDisplayModel(
            roundName = "Final",
            series = listOf(seriesOverview),
        ),
    )

    PocketLeagueTheme {
        Surface {
            BracketPhaseContent(
                phase = BracketPhaseDisplayModel(
                    rounds = rounds,
                ),
            )
        }
    }
}

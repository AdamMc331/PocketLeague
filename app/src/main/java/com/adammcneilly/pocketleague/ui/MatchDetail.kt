package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders the [displayModel] to show detailed information about a match between two teams.
 */
@Composable
fun MatchDetail(
    displayModel: MatchDetailDisplayModel,
    games: List<GameDetailDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TeamResultsOverview(displayModel)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            textAlign = TextAlign.Center,
            text = displayModel.date,
        )

        Divider()

        GameList(games)
    }
}

@Composable
private fun GameList(
    games: List<GameDetailDisplayModel>,
) {
    games.forEach { gameDetailDisplayModel ->
        GameListItem(
            displayModel = gameDetailDisplayModel,
        )

        Divider()
    }
}

@Composable
private fun TeamResultsOverview(displayModel: MatchDetailDisplayModel) {
    CompositionLocalProvider(
        LocalTextStyle provides MaterialTheme.typography.headlineMedium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = displayModel.blueTeamResult.team.name,
            )

            Text(
                text = displayModel.blueTeamResult.score,
            )

            Text(
                text = ":",
            )

            Text(
                text = displayModel.orangeTeamResult.score,
            )

            Text(
                text = displayModel.orangeTeamResult.team.name,
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
@Suppress("UnusedPrivateMember", "LongMethod")
private fun MatchDetailPreview() {
    val g2 = TeamOverviewDisplayModel(
        name = "G2",
    )

    val nv = TeamOverviewDisplayModel(
        name = "NV",
    )

    val matchDetailDisplayModel = MatchDetailDisplayModel(
        orangeTeamResult = MatchTeamResultDisplayModel(
            team = nv,
            score = "1",
        ),
        blueTeamResult = MatchTeamResultDisplayModel(
            team = g2,
            score = "4",
        ),
        date = "May 15, 2022 - 13:00 EDT",
    )

    val games = listOf(
        GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel(
                team = nv,
                score = "1",
                winner = true,
            ),
            blueTeamResult = GameTeamResultDisplayModel(
                team = g2,
                score = "0",
                winner = false,
            ),
            map = "Mannfield (Night)",
        ),
        GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel(
                team = nv,
                score = "0",
                winner = false,
            ),
            blueTeamResult = GameTeamResultDisplayModel(
                team = g2,
                score = "3",
                winner = true,
            ),
            map = "Aquadome",
        ),
        GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel(
                team = nv,
                score = "1",
                winner = false,
            ),
            blueTeamResult = GameTeamResultDisplayModel(
                team = g2,
                score = "4",
                winner = true,
            ),
            map = "DFH Stadium",
        ),
        GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel(
                team = nv,
                score = "0",
                winner = false,
            ),
            blueTeamResult = GameTeamResultDisplayModel(
                team = g2,
                score = "1",
                winner = true,
            ),
            map = "Utopia Coliseum (Dusk)",
        ),
        GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel(
                team = nv,
                score = "1",
                winner = false,
            ),
            blueTeamResult = GameTeamResultDisplayModel(
                team = g2,
                score = "2",
                winner = true,
            ),
            map = "Wasteland (Night)",
        ),
    )

    PocketLeagueTheme {
        Surface {
            MatchDetail(
                displayModel = matchDetailDisplayModel,
                games = games,
            )
        }
    }
}

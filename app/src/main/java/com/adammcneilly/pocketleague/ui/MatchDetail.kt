package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    val selectedGame: MutableState<GameDetailDisplayModel?> = remember {
        mutableStateOf(null)
    }

    selectedGame.value?.let { game ->
        GameDetailDialog(
            displayModel = game,
            onDismissRequest = {
                selectedGame.value = null
            },
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        MatchDetailHeader(
            displayModel = displayModel,
            modifier = Modifier
                .padding(24.dp),
        )

        Text(
            text = "Games",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = 24.dp),
        )

        GameList(
            games = games,
            onGameClicked = { game ->
                selectedGame.value = game
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameList(
    games: List<GameDetailDisplayModel>,
    onGameClicked: (GameDetailDisplayModel) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(24.dp),
    ) {
        games.forEachIndexed { index, gameDetailDisplayModel ->
            GameListItem(
                displayModel = gameDetailDisplayModel,
                modifier = Modifier.clickable {
                    onGameClicked.invoke(gameDetailDisplayModel)
                }
            )

            if (index != games.lastIndex) {
                Divider()
            }
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
        eventName = "RLCS 2021-2022 North America Regional 3",
        stageName = "Main Event",
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

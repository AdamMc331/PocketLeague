package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.components.EmptyStateCard
import com.adammcneilly.pocketleague.android.designsystem.game.GameListItem
import com.adammcneilly.pocketleague.android.designsystem.matches.MatchDetailHeader
import com.adammcneilly.pocketleague.android.designsystem.players.TeamRosterCard
import com.adammcneilly.pocketleague.android.designsystem.stats.CoreStatsComparisonCard
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

/**
 * Renders the [displayModel] to show detailed information about a match between two teams.
 */
@Composable
@Suppress("LongMethod")
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

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        item {
            MatchDetailHeader(
                displayModel = displayModel,
                modifier = Modifier
                    .padding(24.dp),
            )
        }

        item {
            Text(
                text = "Games",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = 24.dp),
            )
        }

        if (games.isNotEmpty()) {
            item {
                GameList(
                    games = games,
                    onGameClicked = { game ->
                        selectedGame.value = game
                    },
                )
            }
        } else {
            item {
                EmptyStateCard(
                    text = "Game information is not yet available.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textModifier = Modifier
                        .padding(32.dp),
                )
            }
        }

        teamRosters(displayModel)

        matchStats(displayModel)
    }
}

private fun LazyListScope.matchStats(displayModel: MatchDetailDisplayModel) {
    val blueStats = displayModel.blueTeamResult.coreStats ?: return
    val orangeStats = displayModel.orangeTeamResult.coreStats ?: return

    item {
        Text(
            text = "Match Stats",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = 24.dp),
        )
    }

    item {
        CoreStatsComparisonCard(
            blueTeamStats = blueStats,
            orangeTeamStats = orangeStats,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

private fun LazyListScope.teamRosters(displayModel: MatchDetailDisplayModel) {
    val noBluePlayers = displayModel.blueTeamResult.players.isEmpty()
    val noOrangePlayers = displayModel.orangeTeamResult.players.isEmpty()

    if (noBluePlayers || noOrangePlayers) {
        return
    }

    item {
        Text(
            text = displayModel.blueTeamResult.team.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = 24.dp),
        )
    }

    item {
        TeamRosterCard(
            players = displayModel.blueTeamResult.players.map {
                it.player
            },
            teamColor = rlcsBlue,
            modifier = Modifier
                .padding(24.dp),
        )
    }

    item {
        Text(
            text = displayModel.orangeTeamResult.team.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = 24.dp),
        )
    }

    item {
        TeamRosterCard(
            players = displayModel.orangeTeamResult.players.map {
                it.player
            },
            teamColor = rlcsOrange,
            modifier = Modifier
                .padding(24.dp),
        )
    }
}

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
                },
            )

            if (index != games.lastIndex) {
                Divider()
            }
        }
    }
}

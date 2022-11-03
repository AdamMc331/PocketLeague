package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.adammcneilly.pocketleague.android.designsystem.stats.CoreStatsComparisonCard
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

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

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
//            .verticalScroll(rememberScrollState()),
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
                    }
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
                        .padding(32.dp)
                )
            }
        }

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
                blueTeamStats = displayModel.blueTeamResult.coreStats,
                orangeTeamStats = displayModel.orangeTeamResult.coreStats,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
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
                }
            )

            if (index != games.lastIndex) {
                Divider()
            }
        }
    }
}

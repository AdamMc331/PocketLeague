package com.adammcneilly.pocketleague.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

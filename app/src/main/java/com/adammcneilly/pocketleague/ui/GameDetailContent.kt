package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GamePlayerResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders a [displayModel] to show detailed information about a game.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailContent(
    displayModel: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    val blueTeamSelected = remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        GameNumberHeader(displayModel)

        TeamTabs(
            displayModel = displayModel,
            blueTeamSelected = blueTeamSelected.value,
            onBlueTeamSelected = {
                blueTeamSelected.value = true
            },
            onOrangeTeamSelected = {
                blueTeamSelected.value = false
            },
        )

        val playerStats = if (blueTeamSelected.value) {
            displayModel.blueTeamResult.players
        } else {
            displayModel.orangeTeamResult.players
        }

        PlayerStatsTable(
            displayModels = playerStats,
        )
    }
}

@Composable
private fun GameNumberHeader(displayModel: GameDetailDisplayModel) {
    Text(
        text = "Game ${displayModel.gameNumber}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun TeamTabs(
    displayModel: GameDetailDisplayModel,
    blueTeamSelected: Boolean,
    onBlueTeamSelected: () -> Unit,
    onOrangeTeamSelected: () -> Unit,
) {
    val selectedIndex = if (blueTeamSelected) {
        0
    } else {
        1
    }

    TabRow(selectedTabIndex = selectedIndex) {
        Tab(
            selected = blueTeamSelected,
            onClick = onBlueTeamSelected,
        ) {
            InlineIconText(
                text = displayModel.blueTeamResult.team.name,
                icon = Icons.Default.EmojiEvents,
                showIcon = displayModel.blueTeamResult.winner,
                modifier = Modifier.padding(8.dp),
            )
        }

        Tab(
            selected = !blueTeamSelected,
            onClick = onOrangeTeamSelected,
        ) {
            InlineIconText(
                text = displayModel.orangeTeamResult.team.name,
                icon = Icons.Default.EmojiEvents,
                showIcon = displayModel.orangeTeamResult.winner,
                modifier = Modifier.padding(8.dp),
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
private fun GameDetailContentPreview() {
    val trk = GamePlayerResultDisplayModel(
        playerName = "trk511",
        coreStats = CoreStatsDisplayModel(
            score = "342",
            goals = "1",
            assists = "0",
            saves = "1",
            shots = "5",
        ),
    )

    val ahmad = GamePlayerResultDisplayModel(
        playerName = "Ahmad",
        coreStats = CoreStatsDisplayModel(
            score = "1,042",
            goals = "10",
            assists = "0",
            saves = "1",
            shots = "10",
        ),
    )

    val okhalid = GamePlayerResultDisplayModel(
        playerName = "oKhaliD",
        coreStats = CoreStatsDisplayModel(
            score = "100",
            goals = "0",
            assists = "0",
            saves = "1",
            shots = "0",
        ),
    )

    val game = GameDetailDisplayModel(
        orangeTeamResult = GameTeamResultDisplayModel(
            score = "1",
            winner = true,
            team = TeamOverviewDisplayModel(
                name = "FaZe",
            ),
        ),
        blueTeamResult = GameTeamResultDisplayModel(
            score = "0",
            team = TeamOverviewDisplayModel(
                name = "Pittsburgh Knights",
            ),
            players = listOf(
                ahmad,
                trk,
                okhalid,
            ),
        ),
        map = "DFH Stadium",
        gameNumber = "1",
    )

    PocketLeagueTheme {
        Surface {
            GameDetailContent(
                displayModel = game,
                modifier = Modifier.padding(24.dp),
            )
        }
    }
}

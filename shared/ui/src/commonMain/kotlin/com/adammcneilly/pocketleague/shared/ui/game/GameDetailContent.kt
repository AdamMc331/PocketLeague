package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.InlineIconText
import com.adammcneilly.pocketleague.shared.ui.stats.PlayerStatsTable

/**
 * Renders a [displayModel] to show detailed information about a game.
 */
@Composable
fun GameDetailContent(
    displayModel: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    val blueTeamSelected = remember { mutableStateOf(true) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
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

package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.game.GameDetailDialog
import com.adammcneilly.pocketleague.shared.ui.game.GameListCard
import com.adammcneilly.pocketleague.shared.ui.stats.CoreStatsComparisonCard

/**
 * Compose content for the match detail screen.
 */
@Composable
fun MatchDetailContent(
    match: MatchDetailDisplayModel,
    games: List<GameDetailDisplayModel>,
    selectedGame: GameDetailDisplayModel?,
    onSelectedGameDismissed: () -> Unit,
    onGameClicked: (GameDetailDisplayModel) -> Unit,
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selectedGame != null) {
        GameDetailDialog(
            game = selectedGame,
            onDismissRequest = onSelectedGameDismissed,
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(PocketLeagueTheme.sizes.screenPadding),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        matchDetailHeader(match, onTeamClicked)

        gamesSection(games, onGameClicked)

        statsSection(match)
    }
}

private fun LazyListScope.statsSection(match: MatchDetailDisplayModel) {
    val blueTeamCoreStats = match.blueTeamResult.coreStats
    val orangeTeamCoreStats = match.orangeTeamResult.coreStats

    if (blueTeamCoreStats != null && orangeTeamCoreStats != null) {
        item {
            SectionHeader(
                text = "Stat Comparison",
            )
        }

        item {
            CoreStatsComparisonCard(
                blueTeamStats = blueTeamCoreStats,
                orangeTeamStats = orangeTeamCoreStats,
            )
        }
    }
}

private fun LazyListScope.gamesSection(
    games: List<GameDetailDisplayModel>,
    onGameClicked: (GameDetailDisplayModel) -> Unit,
) {
    gamesHeader()

    gamesList(games, onGameClicked)
}

private fun LazyListScope.gamesHeader() {
    item {
        SectionHeader(
            text = "Games",
        )
    }
}

private fun LazyListScope.gamesList(
    games: List<GameDetailDisplayModel>,
    onGameClicked: (GameDetailDisplayModel) -> Unit,
) {
    item {
        GameListCard(
            games = games,
            onGameClicked = onGameClicked,
        )
    }
}

private fun LazyListScope.matchDetailHeader(
    match: MatchDetailDisplayModel,
    onTeamClicked: (String) -> Unit,
) {
    item {
        MatchDetailHeader(
            displayModel = match,
            onTeamClicked = onTeamClicked,
        )
    }
}

@Composable
private fun SectionHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}

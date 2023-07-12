package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.game.GameListCard

/**
 * Compose content for the match detail screen.
 */
@Composable
fun MatchDetailContent(
    match: MatchDetailDisplayModel,
    games: List<GameDetailDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(PocketLeagueTheme.sizes.screenPadding),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        item {
            MatchDetailHeader(
                displayModel = match,
            )
        }

        item {
            GameListCard(
                games = games,
            )
        }
    }
}

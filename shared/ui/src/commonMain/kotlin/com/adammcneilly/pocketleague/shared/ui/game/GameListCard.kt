package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.ListItemDividerCard

/**
 * Renders a list of [games] inside of a card component.
 */
@Composable
fun GameListCard(
    games: List<GameDetailDisplayModel>,
    onGameClicked: (GameDetailDisplayModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItemDividerCard(
        items = games,
        modifier = modifier,
    ) { game ->
        GameListItem(
            displayModel = game,
            modifier = Modifier
                .clickable {
                    onGameClicked.invoke(game)
                },
        )
    }
}

package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel

/**
 * Renders a list of [games] inside of a card component.
 */
@Composable
fun GameListCard(
    games: List<GameDetailDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column {
            games.forEachIndexed { index, game ->
                GameListItem(game)

                if (index != games.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

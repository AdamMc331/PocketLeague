package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailViewState

/**
 * The UI content of the match detail screen.
 */
@Composable
fun MatchDetailContent(
    viewState: MatchDetailViewState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        itemsIndexed(viewState.games) { index, game ->
            GameListItem(game = game)

            if (index != viewState.games.lastIndex) {
                Divider()
            }
        }
    }
}

package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.android.designsystem.teamselection.TeamSelectionListItem
import com.adammcneilly.pocketleague.android.designsystem.teamselection.TeamSelectionListItemClickListener
import com.adammcneilly.pocketleague.shared.screens.teamselection.TeamSelectionViewState

/**
 * This renders the given [viewState] to show a selection of teams that the user can favorite.
 */
@Composable
fun TeamSelectionContent(
    viewState: TeamSelectionViewState,
    listItemClickListener: TeamSelectionListItemClickListener,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val teams = viewState.teams

        itemsIndexed(teams) { index, team ->
            TeamSelectionListItem(
                team = team,
                isFavorite = viewState.isFavorite(team),
                clickListener = listItemClickListener,
            )

            if (index != teams.lastIndex) {
                Divider()
            }
        }
    }
}

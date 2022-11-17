package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.teamselection.TeamSelectionViewState

/**
 * This renders the given [viewState] to show a selection of teams that the user can favorite.
 */
@Composable
fun TeamSelectionContent(
    viewState: TeamSelectionViewState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(viewState.teams.orEmpty()) { team ->
            Text(
                text = team.name,
            )
        }
    }
}

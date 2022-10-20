package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.myteams.FavoriteTeamRowItem
import com.adammcneilly.pocketleague.shared.screens.myteams.MyTeamsViewState

/**
 * Render the supplied [viewState] to show the user's teams.
 */
@Composable
fun MyTeamsContent(
    viewState: MyTeamsViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(viewState.teams) { team ->
                FavoriteTeamRowItem(displayModel = team)
            }
        }
    }
}

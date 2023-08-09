package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * UI content for the team detail screen with the given [state].
 */
@Composable
internal fun TeamDetailContent(
    state: TeamDetailScreen.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = PocketLeagueTheme.sizes.screenPadding,
        ),
    ) {
        item {
            TeamHeaderListItem(
                team = state.team,
            )
        }
    }
}

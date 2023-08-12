package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.utils.screenHorizontalPadding

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
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        item {
            TeamHeaderListItem(
                team = state.team,
            )
        }

        rosterCardSection(state)
    }
}

private fun LazyListScope.rosterCardSection(state: TeamDetailScreen.State) {
    item {
        // Unify the headers across each screen
        Text(
            text = "Roster",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .screenHorizontalPadding(),
        )
    }

    item {
        RosterCard(
            roster = state.roster,
            modifier = Modifier
                .fillMaxWidth()
                .screenHorizontalPadding(),
        )
    }
}

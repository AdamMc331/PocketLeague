package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.matches.MatchesCarousel
import com.adammcneilly.pocketleague.android.designsystem.matches.RecentMatchesEmptyState
import com.adammcneilly.pocketleague.android.designsystem.myteams.AddFavoriteTeamsCTA
import com.adammcneilly.pocketleague.android.designsystem.myteams.FavoriteTeamRowItem
import com.adammcneilly.pocketleague.shared.screens.myteams.MyTeamsViewState

/**
 * Render the supplied [viewState] to show the user's teams.
 */
@Composable
fun MyTeamsContent(
    viewState: MyTeamsViewState,
    onAddTeamClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val teams = viewState.teams

        if (teams != null) {
            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(teams) { team ->
                    FavoriteTeamRowItem(displayModel = team)
                }

                item {
                    AddFavoriteTeamsCTA(
                        onClick = onAddTeamClicked,
                    )
                }
            }
        } else {
            // Render error UI.
        }

        Text(
            text = "Recent Matches",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp),
        )

        val recentMatches = viewState.recentMatches

        if (recentMatches != null) {
            if (recentMatches.isNotEmpty()) {
                MatchesCarousel(
                    matches = recentMatches,
                    onMatchClicked = {
                        // Coming soon!
                    },
                )
            } else {
                RecentMatchesEmptyState()
            }
        } else {
            // Render error UI.
        }
    }
}

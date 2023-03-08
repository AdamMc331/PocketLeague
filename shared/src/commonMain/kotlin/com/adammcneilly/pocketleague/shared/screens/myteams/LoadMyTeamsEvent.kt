package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadMyTeams() = screenCoroutine { scope ->
    fetchFavoriteTeams(scope)
}

private fun Events.fetchFavoriteTeams(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .teamRepository
        .getFavoriteTeams()
        .onEach { favoriteTeamsList ->
            stateManager.updateScreen(MyTeamsViewState::class) { currentState ->
                currentState.copy(
                    teams = favoriteTeamsList.map(Team::toOverviewDisplayModel),
                )
            }
        }
        .flatMapLatest { teams ->
            val teamIds = teams.map(Team::id)

            appModule
                .dataModule
                .matchRepository
                .getPastWeeksMatchesForTeams(teamIds)
        }
        .onEach { matchList ->
            stateManager.updateScreen(MyTeamsViewState::class) { currentState ->
                currentState.copy(
                    recentMatches = matchList.map(Match::toDetailDisplayModel),
                )
            }
        }
        .launchIn(scope)
}

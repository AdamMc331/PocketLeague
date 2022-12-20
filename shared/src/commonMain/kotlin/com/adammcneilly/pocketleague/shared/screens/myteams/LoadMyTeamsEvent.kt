package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.util.asFlowList
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.Clock

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadMyTeams() = screenCoroutine { scope ->
    fetchFavoriteTeams(scope)
}

private suspend fun Events.fetchFavoriteTeams(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .database
        .localTeamQueries
        .selectFavorites()
        .asFlowList(LocalTeam::toTeam)
        .onEach { favoriteTeamsList ->
            stateManager.updateScreen(MyTeamsViewState::class) { currentState ->
                currentState.copy(
                    teams = favoriteTeamsList.map(Team::toOverviewDisplayModel),
                )
            }

            fetchAllRecentMatches(favoriteTeamsList, scope)
        }
        .launchIn(scope)
}

private suspend fun Events.fetchAllRecentMatches(
    teams: List<Team>,
    scope: CoroutineScope,
) {
    val matchesRequests = teams.map { team ->
        scope.async {
            fetchRecentMatchesForTeam(team)
        }
    }

    @Suppress("SpreadOperator")
    val allMatchResults = awaitAll(*matchesRequests.toTypedArray())

    // Sort and dedupe matches
    // This doesn't handle error case, we should do that separately.
    val sortedMatches = allMatchResults
        .filterIsInstance<DataState.Success<List<Match>>>()
        .flatMap(DataState.Success<List<Match>>::data)
        .toSet()
        .sortedByDescending(Match::dateUTC)
        .map(Match::toDetailDisplayModel)

    stateManager.updateScreen(MyTeamsViewState::class) { currentState ->
        currentState.copy(
            recentMatches = sortedMatches,
        )
    }
}

/**
 * Given a specific [team], request the recent matches for that team and return
 * that data state to be combined with other teams in [fetchAllRecentMatches].
 */
private suspend fun Events.fetchRecentMatchesForTeam(
    team: Team,
): DataState<List<Match>> {
    val matchListRequest = MatchListRequest(
        team = team.id,
        sort = "date:desc",
        before = Clock.System.now(),
    )

    // In the future we need to observe matches from our DB.

    return DataState.Success(emptyList())
}

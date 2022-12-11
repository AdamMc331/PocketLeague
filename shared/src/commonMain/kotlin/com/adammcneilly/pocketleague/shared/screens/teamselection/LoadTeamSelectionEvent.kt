package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadTeamSelection() = screenCoroutine {
    fetchFavoriteTeams(it)
    fetchActiveTeams(it)
}

private fun Events.fetchActiveTeams(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .teamRepository
        .getActiveRLCSTeams()
        .onEach { teamList ->
            stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
                currentState.copy(
                    activeTeamsDataState = DataState.Success(
                        data = teamList.map(Team::toOverviewDisplayModel)
                    ),
                )
            }
        }
        .launchIn(scope)
}

private fun Events.fetchFavoriteTeams(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .teamRepository
        .getFavoriteTeams()
        .onEach { teamList ->
            val displayModelList = teamList.map(Team::toOverviewDisplayModel)

            stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
                currentState.copy(
                    favoriteTeams = displayModelList,
                )
            }
        }
        .launchIn(scope)
}

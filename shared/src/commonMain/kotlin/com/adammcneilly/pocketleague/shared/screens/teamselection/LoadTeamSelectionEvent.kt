package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadTeamSelection() = screenCoroutine {
    fetchFavoriteTeams()
    fetchActiveTeams()
}

private suspend fun Events.fetchActiveTeams() {
    val activeTeamsDataState = repository
        .teamService
        .getActiveRLCSTeams()

    stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
        currentState.copy(
            activeTeamsDataState = activeTeamsDataState.map { teamList ->
                teamList.map(Team::toOverviewDisplayModel)
            },
        )
    }
}

private suspend fun Events.fetchFavoriteTeams() {
    val activeTeamsDataState = repository
        .teamService
        .getFavoriteTeams()

    val teamList: List<Team> = when (activeTeamsDataState) {
        is DataState.Error,
        DataState.Loading -> {
            emptyList()
        }
        is DataState.Success -> {
            activeTeamsDataState.data
        }
    }

    val displayModelList = teamList.map(Team::toOverviewDisplayModel)

    stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
        currentState.copy(
            favoriteTeams = displayModelList,
        )
    }
}

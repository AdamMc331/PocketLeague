package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadTeamSelection() = screenCoroutine {
    fetchActiveTeams()
}

private suspend fun Events.fetchActiveTeams() {
    val activeTeamsDataState = repository
        .teamService
        .getActiveRLCSTeams()

    stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
        currentState.copy(
            teamsDataState = activeTeamsDataState.map { teamList ->
                teamList.map(Team::toOverviewDisplayModel)
            },
        )
    }
}

package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadTeamSelection() = screenCoroutine {
    fetchActiveTeams(it)
}

private fun Events.fetchActiveTeams(
    scope: CoroutineScope
) {
    appModule
        .dataModule
        .teamRepository
        .getActiveRLCSTeams()
        .onEach { teamList ->
            stateManager.updateScreen(TeamSelectionViewState::class) { currentState ->
                currentState.copy(
                    teams = teamList.map(Team::toOverviewDisplayModel)
                )
            }
        }
        .launchIn(scope)
}

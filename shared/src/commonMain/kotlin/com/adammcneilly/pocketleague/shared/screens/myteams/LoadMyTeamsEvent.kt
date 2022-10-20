package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.delay

/**
 * Load the information to populate the my teams screen.
 */
fun Events.loadMyTeams() = screenCoroutine {
    delay(3_000)

    val favoriteTeamsDataState = repository
        .teamService
        .getFavoriteTeams()
        .map { teamList ->
            teamList.map(Team::toOverviewDisplayModel)
        }

    stateManager.updateScreen(MyTeamsViewState::class) { currentState ->
        currentState.copy(
            teamsDataState = favoriteTeamsDataState,
        )
    }
}

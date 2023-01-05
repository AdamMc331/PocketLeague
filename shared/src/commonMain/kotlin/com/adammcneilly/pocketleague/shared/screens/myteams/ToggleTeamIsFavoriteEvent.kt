package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.shared.screens.Events

/**
 * Given a [teamId] update it's favorite status to the given [isFavorite] param.
 */
fun Events.updateTeamIsFavorite(
    teamId: String,
    isFavorite: Boolean,
) = screenCoroutine {
    appModule
        .dataModule
        .teamRepository
        .updateIsFavorite(teamId, isFavorite)
}

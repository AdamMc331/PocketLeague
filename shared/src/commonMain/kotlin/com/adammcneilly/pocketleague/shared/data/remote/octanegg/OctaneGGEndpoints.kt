package com.adammcneilly.pocketleague.shared.data.remote.octanegg

/**
 * Defines all the statis endpoints for various octane.gg domains.
 */
object OctaneGGEndpoints {
    const val EVENTS = "/events"
    const val MATCHES = "/matches"

    /**
     * Builds and endpoint for retrieving games by [matchId].
     */
    fun gamesByMatchEndpoint(matchId: String): String {
        return "/matches/$matchId/games"
    }
}

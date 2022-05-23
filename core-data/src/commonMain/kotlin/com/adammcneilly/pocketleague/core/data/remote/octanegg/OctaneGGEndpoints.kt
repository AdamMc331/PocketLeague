package com.adammcneilly.pocketleague.core.data.remote.octanegg

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

    /**
     * Builds an endpoint for retrieving participants by [eventId].
     */
    fun eventParticipantsEndpoint(eventId: String): String {
        return "/events/$eventId/participants"
    }
}

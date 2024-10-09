package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Defines an enumeration of the ways we can request a list of matches
 * in the PocketLeague app.
 */
data class MatchListRequest(
    val matchId: Match.Id? = null,
    val startDateUTC: String? = null,
    val endDateUTC: String? = null,
    val teamId: String? = null,
    val eventId: Event.Id? = null,
    val stageId: com.adammcneilly.pocketleague.core.models.EventStage.Id? = null,
)

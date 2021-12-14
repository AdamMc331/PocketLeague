package com.adammcneilly.pocketleague.event.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.domain.models.Event
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage

/**
 * A collection of data related methods to fetch information about an event.
 */
interface EventService {

    /**
     * Given an [eventName], request all information about the swiss stage of that event.
     */
    suspend fun fetchSwissStage(
        eventName: String,
    ): Result<SwissStage>

    /**
     * Given a [leagueSlug], find all [Event] entities for that league.
     */
    suspend fun fetchAllEvents(
        leagueSlug: String,
    ): Result<List<Event>>
}

package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the match space.
 */
interface MatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    suspend fun fetchMatchDetail(
        matchId: Match.Id,
    ): Result<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    fun getPastWeeksMatches(): Flow<List<Match>>

    /**
     * Retrieve a list of match entities that haven't happened yet.
     */
    fun getUpcomingMatches(): Flow<List<Match>>

    /**
     * Retrieves all matches that occured in the given [eventId] and [stageId].
     */
    fun getMatchesForEventStage(
        eventId: Event.Id,
        stageId: EventStage.Id,
    ): Flow<List<Match>>
}

package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Data contract for requesting match information from a local data source.
 */
interface MatchRepository {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    fun getMatchDetail(
        matchId: Match.Id,
    ): Flow<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    fun getMatchesInDateRange(
        startDateUTC: String,
        endDateUTC: String,
    ): Flow<List<Match>>

    /**
     * Retrieve a list of match entities that haven't happened yet.
     */
    fun getUpcomingMatches(): Flow<List<Match>>

    /**
     * Unlike [getUpcomingMatches], this makes a singular request to fetch upcoming matches.
     */
    suspend fun fetchAndPersistUpcomingMatches(): Result<Unit>

    /**
     * Retrieves all matches that occurred in the given [eventId] and [stageId].
     */
    fun getMatchesForEventStage(
        eventId: Event.Id,
        stageId: EventStage.Id,
    ): Flow<List<Match>>

    /**
     * Retrieve a list of match entities that have happened over
     * the last week, where one of the participants is in the [teamIds].
     */
    fun getPastWeeksMatchesForTeams(
        teamIds: List<String>,
    ): Flow<List<Match>>
}

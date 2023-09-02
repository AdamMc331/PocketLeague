package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [MatchRepository] that first requests data from the [localDataSource]
 * and syncs with the [remoteDataSource].
 */
class OfflineFirstMatchRepository(
    private val localDataSource: LocalMatchService,
    private val remoteDataSource: RemoteMatchService,
    private val remoteMatchFetcher: RemoteMatchFetcher,
) : MatchRepository {

    private val storeVersion = StoreMatchRepository(
        remoteMatchFetcher = remoteMatchFetcher,
        localMatchService = localDataSource,
    )

    override fun getMatchDetail(matchId: Match.Id): Flow<Match> {
        val request = MatchListRequest.Id(matchId)

        return storeVersion.stream(request).map(List<Match>::first)
    }

    override fun getMatchesInDateRange(
        startDateUTC: String,
        endDateUTC: String,
    ): Flow<List<Match>> {
        val request = MatchListRequest.DateRange(
            startDateUTC = startDateUTC,
            endDateUTC = endDateUTC,
        )

        return storeVersion.stream(request)
    }

    override fun getUpcomingMatches(): Flow<List<Match>> {
        return localDataSource
            .getUpcomingMatches()
            .onStart {
                fetchAndPersistUpcomingMatches()
            }
    }

    override suspend fun fetchAndPersistUpcomingMatches(): Result<Unit> {
        val remoteResponse = remoteDataSource.getUpcomingMatches()

        remoteResponse.fold(
            onSuccess = { matches ->
                localDataSource.insertMatches(matches)
                return Result.success(Unit)
            },
            onFailure = { error ->
                println("Unable to request past week's matches: ${error.message}")
                return Result.failure(error)
            },
        )
    }

    /**
     * For the start API, what we call a "stage", they define as an "event", so it's important
     * that the [stageId] is what is passed into this query.
     */
    override fun getMatchesForEventStage(eventId: Event.Id, stageId: EventStage.Id): Flow<List<Match>> {
        val request = MatchListRequest.EventStage(
            eventId = eventId,
            stageId = stageId,
        )

        return storeVersion.stream(request)
    }

    override fun getPastWeeksMatchesForTeams(teamIds: List<String>): Flow<List<Match>> {
        return localDataSource
            .getPastWeeksMatchesForTeams(teamIds)
    }
}

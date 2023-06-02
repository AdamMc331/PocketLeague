package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [MatchRepository] that first requests data from the [localDataSource]
 * and syncs with the [remoteDataSource].
 */
class OfflineFirstMatchRepository(
    private val localDataSource: LocalMatchService,
    private val remoteDataSource: RemoteMatchService,
) : MatchRepository {

    override fun getMatchDetail(matchId: String): Flow<Match> {
        return localDataSource
            .getMatchDetail(matchId)
            .onStart {
                fetchAndPersistMatchDetail(matchId)
            }
    }

    private suspend fun fetchAndPersistMatchDetail(matchId: String) {
        val remoteResponse = remoteDataSource.getMatchDetail(matchId)

        remoteResponse.fold(
            onSuccess = { matches ->
                localDataSource.insertMatches(listOf(matches))
            },
            onFailure = {
                println("Unable to request remote match: $matchId")
            },
        )
    }

    override fun getPastWeeksMatches(): Flow<List<Match>> {
        return localDataSource
            .getPastWeeksMatches()
            .onStart {
                fetchAndPersistPastWeeksMatches()
            }
    }

    private suspend fun fetchAndPersistPastWeeksMatches() {
        val remoteResponse = remoteDataSource.getPastWeeksMatches()

        remoteResponse.fold(
            onSuccess = { matches ->
                localDataSource.insertMatches(matches)
            },
            onFailure = { error ->
                println("Unable to request past week's matches: ${error.message}")
            },
        )
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
    override fun getMatchesForEventStage(eventId: String, stageId: String): Flow<List<Match>> {
        // Commented out because I want to remember this note:
        /**
         * For the start API, what we call a "stage", they define as an "event", so it's important
         * that the [stageId] is what is passed into this query.
         */
//        return flow {
//            val response = StartGGApolloClient.query(EventSetsQuery(Optional.present(stageId))).execute()
//
//            val matches = response.data?.event?.sets?.nodes?.mapNotNull {
//                it?.setFragment?.toMatch()
//            }.orEmpty()
//
//            emit(matches)
//        }
        return localDataSource
            .getMatchesForEventStage(eventId, stageId)
            .onStart {
                fetchAndPersistMatchesForEventStage(eventId, stageId)
            }
    }

    private suspend fun fetchAndPersistMatchesForEventStage(eventId: String, stageId: String) {
        val remoteResponse = remoteDataSource.getMatchesForEventStage(eventId, stageId)

        remoteResponse.fold(
            onSuccess = { matches ->
                localDataSource.insertMatches(matches)
            },
            onFailure = { error ->
                println("Unable to request event stage matches: ${error.message}")
            },
        )
    }

    override fun getPastWeeksMatchesForTeams(teamIds: List<String>): Flow<List<Match>> {
        return localDataSource
            .getPastWeeksMatchesForTeams(teamIds)
    }
}

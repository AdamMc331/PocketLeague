package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [MatchRepository] that first requests data from the [localDataSource]
 * and syncs with the [remoteDataSource].
 */
class OfflineFirstMatchRepository(
    private val localDataSource: LocalMatchService,
    private val remoteDataSource: RemoteMatchService
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

        when (remoteResponse) {
            is DataState.Error -> {
                println("Unable to request remote match: $matchId")
            }

            is DataState.Success -> {
                localDataSource.insertMatches(listOf(remoteResponse.data))
            }
        }
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

        when (remoteResponse) {
            is DataState.Error -> {
                println("Unable to request past week's matches: ${remoteResponse.error.message}")
            }

            is DataState.Success -> {
                localDataSource.insertMatches(remoteResponse.data)
            }
        }
    }

    override fun getUpcomingMatches(): Flow<List<Match>> {
        return localDataSource
            .getUpcomingMatches()
            .onStart {
                fetchAndPersistUpcomingMatches()
            }
    }

    override suspend fun fetchAndPersistUpcomingMatches(): DataState<Unit> {
        val remoteResponse = remoteDataSource.getUpcomingMatches()

        return when (remoteResponse) {
            is DataState.Error -> {
                println("Unable to request past week's matches: ${remoteResponse.error.message}")

                DataState.Error(remoteResponse.error)
            }

            is DataState.Success -> {
                localDataSource.insertMatches(remoteResponse.data)

                DataState.Success(Unit)
            }
        }
    }

    override fun getMatchesForEventStage(eventId: String, stageId: String): Flow<List<Match>> {
        return localDataSource
            .getMatchesForEventStage(eventId, stageId)
            .onStart {
                fetchAndPersistMatchesForEventStage(eventId, stageId)
            }
    }

    private suspend fun fetchAndPersistMatchesForEventStage(eventId: String, stageId: String) {
        val remoteResponse = remoteDataSource.getMatchesForEventStage(eventId, stageId)

        when (remoteResponse) {
            is DataState.Error -> {
                println("Unable to request event stage matches: ${remoteResponse.error.message}")
            }

            is DataState.Success -> {
                localDataSource.insertMatches(remoteResponse.data)
            }
        }
    }
}

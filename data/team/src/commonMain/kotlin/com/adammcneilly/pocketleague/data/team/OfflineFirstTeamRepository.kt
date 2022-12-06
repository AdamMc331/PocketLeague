package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [TeamRepository] that uses the [localDataSource] as the source of
 * truth but syncs data with our [remoteDataSource] as necessary.
 */
class OfflineFirstTeamRepository(
    private val localDataSource: TeamRepository,
    private val remoteDataSource: TeamRepository,
) : TeamRepository {

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return localDataSource
            .getFavoriteTeams()
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        return localDataSource
            .getActiveRLCSTeams()
            .onStart {
                remoteDataSource
                    .getActiveRLCSTeams()
                    .collect { teams ->
                        localDataSource.insertTeams(teams)
                    }
            }
    }

    override suspend fun insertTeams(teams: List<Team>) {
        localDataSource.insertTeams(teams)
    }
}

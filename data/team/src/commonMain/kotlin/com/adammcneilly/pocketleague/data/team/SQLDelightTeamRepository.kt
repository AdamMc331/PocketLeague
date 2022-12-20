package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowList
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import kotlinx.coroutines.flow.Flow

/**
 * An implementation of [TeamRepository] that reads the necessary data from the
 * supplied sql delight [database].
 */
class SQLDelightTeamRepository(
    private val database: PocketLeagueDB,
) : TeamRepository {

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database
            .localTeamQueries
            .selectFavorites()
            .asFlowList(LocalTeam::toTeam)
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        return database
            .localTeamQueries
            .selectAll()
            .asFlowList(LocalTeam::toTeam)
    }

    override suspend fun insertTeams(teams: List<Team>) {
        teams.forEach { team ->
            database
                .localTeamQueries
                .insertFullTeamObject(team.toLocalTeam())
        }
    }
}

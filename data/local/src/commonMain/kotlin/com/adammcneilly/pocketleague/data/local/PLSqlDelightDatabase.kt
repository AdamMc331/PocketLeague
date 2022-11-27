package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * An implementation of [PocketLeagueDatabase] that uses SQL Delight to power the data requests.
 */
class PLSqlDelightDatabase(databaseDriver: SqlDriver) : PocketLeagueDatabase {
    private val database = PocketLeagueDB(databaseDriver)

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database.localTeamQueries
            .selectFavorites()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override fun getAllTeams(): Flow<List<Team>> {
        return database.localTeamQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override suspend fun storeTeams(teams: List<Team>) {
        database.transaction {
            teams.forEach { team ->
                database
                    .localTeamQueries
                    .insertFullTeamObject(team.toLocalTeam())
            }
        }
    }
}

private fun Team.toLocalTeam(): LocalTeam {
    return LocalTeam(
        id = this.id,
        name = this.name,
        imageURL = this.imageUrl,
        isFavorite = this.isFavorite,
    )
}

private fun LocalTeam.toTeam(): Team {
    return Team(
        id = this.id,
        name = this.name,
        imageUrl = this.imageURL,
        isFavorite = this.isFavorite,
    )
}

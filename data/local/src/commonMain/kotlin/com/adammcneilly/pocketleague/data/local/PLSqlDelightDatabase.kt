package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * An implementation of [PocketLeagueDatabase] that uses SQL Delight to power the data requests.
 */
class PLSqlDelightDatabase(databaseDriverFactory: DatabaseDriverFactory) : PocketLeagueDatabase {
    private val database = PocketLeagueDB(databaseDriverFactory.createDriver())

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database.localTeamQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }
}

private fun LocalTeam.toTeam(): Team {
    return Team(
        id = this.id,
        name = this.name,
        imageUrl = this.imageURL,
    )
}

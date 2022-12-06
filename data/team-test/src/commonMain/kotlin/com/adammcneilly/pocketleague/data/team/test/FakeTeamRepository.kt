package com.adammcneilly.pocketleague.data.team.test

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.team.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * An implementation of [TeamRepository] that allows us to make verifications about if/how things
 * are called.
 */
class FakeTeamRepository : TeamRepository {

    var favoriteTeams: List<Team> = emptyList()
    var favoriteTeamsRequestCount: Int = 0

    var activeTeams: List<Team> = emptyList()
    var activeTeamsRequestCount: Int = 0

    private val _insertedTeams: MutableList<Team> = mutableListOf()

    val insertedTeams: List<Team>
        get() = _insertedTeams.toList()

    override fun getFavoriteTeams(): Flow<List<Team>> {
        favoriteTeamsRequestCount++
        return flowOf(this.favoriteTeams)
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        activeTeamsRequestCount++
        return flowOf(this.activeTeams)
    }

    override suspend fun insertTeams(teams: List<Team>) {
        this._insertedTeams.addAll(teams)
    }
}
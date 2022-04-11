package com.adammcneilly.pocketleague.team.implementation.octanegg

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.team.api.TeamRepository
import com.adammcneilly.pocketleague.team.implementation.octanegg.dtos.TeamDTO
import com.adammcneilly.pocketleague.team.implementation.octanegg.dtos.TeamListResponseDTO
import com.adammcneilly.pocketleague.team.implementation.octanegg.mappers.toTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of [TeamRepository] to request information from the octane.gg API.
 */
class OctaneGGTeamService : TeamRepository {

    private val apiClient = OctaneGGAPIClient()

    override fun fetchActiveTeams(): Flow<DataResult<List<Team>>> {
        return flow {
            val apiResult = apiClient.getResponse<TeamListResponseDTO>("teams/active")

            val mappedResult: DataResult<List<Team>> = when (apiResult) {
                is DataResult.Success -> {
                    val mappedTeams = apiResult.data.teams?.map(TeamDTO::toTeam).orEmpty()

                    DataResult.Success(mappedTeams)
                }
                is DataResult.Error -> {
                    DataResult.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}

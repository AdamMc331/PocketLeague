package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.models.SwissTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.PhaseGroupQuery
import com.adammcneilly.pocketleague.data.startgg.StartGGApolloClient
import com.adammcneilly.pocketleague.data.startgg.mappers.toTeam
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

class StartGGSwissStageRepository(
    private val apiClient: ApolloClient = StartGGApolloClient,
) : SwissStageRepository {
    override suspend fun fetchSwissStageResults(
        stageId: String,
    ): Result<List<SwissTeamResult>> {
        val query = PhaseGroupQuery(
            id = Optional.present(stageId),
        )

        val data = apiClient.query(query).execute().data

        return if (data != null) {
            val resultList: List<SwissTeamResult> = data.phaseGroup?.standings?.nodes?.map { node ->
                SwissTeamResult(
                    team = node?.entrant?.team?.teamFragment?.toTeam() ?: Team(),
                    matchWins = 0,
                    matchLosses = 0,
                )
            }.orEmpty()

            Result.success(resultList)
        } else {
            Result.failure(Throwable("Unable to fetch phase group for: $stageId"))
        }
    }
}

package com.adammcneilly.pocketleague.phase.data.remote

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.smashgg.SmashGGModelMapper
import com.adammcneilly.pocketleague.core.models.PhaseDetail
import com.adammcneilly.pocketleague.graphql.PhaseDetailQuery
import com.adammcneilly.pocketleague.phase.data.PhaseService
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

/**
 * A concrete implementation of a [PhaseService] that fetches information from the Smash.gg [api].
 */
class SmashGGPhaseService(
    private val api: ApolloClient,
    private val modelMapper: SmashGGModelMapper,
) : PhaseService {

    override suspend fun fetchPhaseDetail(phaseId: String): Result<PhaseDetail> {
        val firstQuery = PhaseDetailQuery(
            id = Optional.Present(phaseId),
            perPage = Optional.Present(0),
        )

        val firstResult = api.query(firstQuery).execute()
        val totalItems = firstResult
            .data
            ?.phase
            ?.phaseDetailFragment
            ?.sets
            ?.pageInfo
            ?.total ?: 0

        val secondQuery = PhaseDetailQuery(
            id = Optional.Present(phaseId),
            perPage = Optional.Present(totalItems),
        )

        val result = api.query(secondQuery).execute()

        val phaseDetail = result
            .data
            ?.phase
            ?.phaseDetailFragment
            .let(modelMapper::phaseDetailFragmentToPhaseDetail)

        return Result.Success(
            data = phaseDetail,
        )
    }
}

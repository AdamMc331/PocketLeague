package com.adammcneilly.pocketleague.phase.data.remote

import com.adammcneilly.pocketleague.PhaseDetailQuery
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.smashgg.SmashGGModelMapper
import com.adammcneilly.pocketleague.core.models.PhaseDetail
import com.adammcneilly.pocketleague.phase.data.PhaseService
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await

/**
 * A concrete implementation of a [PhaseService] that fetches information from the Smash.gg [api].
 */
class SmashGGPhaseService(
    private val api: ApolloClient,
    private val modelMapper: SmashGGModelMapper,
) : PhaseService {

    override suspend fun fetchPhaseDetail(phaseId: String): Result<PhaseDetail> {
        val firstQuery = PhaseDetailQuery(
            id = phaseId.toInput(),
            perPage = 0.toInput(),
        )

        val firstResult = api.query(firstQuery).await()
        val totalItems = firstResult
            .data
            ?.phase
            ?.fragments
            ?.phaseDetailFragment
            ?.sets
            ?.pageInfo
            ?.total ?: 0

        val secondQuery = PhaseDetailQuery(
            id = phaseId.toInput(),
            perPage = totalItems.toInput(),
        )

        val result = api.query(secondQuery).await()

        val phaseDetail = result
            .data
            ?.phase
            ?.fragments
            ?.phaseDetailFragment
            .let(modelMapper::phaseDetailFragmentToPhaseDetail)

        return Result.Success(
            data = phaseDetail,
        )
    }
}

package com.adammcneilly.pocketleague.phase.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.phase.data.PhaseService
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail
import javax.inject.Inject

/**
 * A concrete implementation of [FetchPhaseDetailUseCase] that requests information from the supplied
 * [service].
 */
class FetchPhaseDetailUseCaseImpl @Inject constructor(
    private val service: PhaseService,
) : FetchPhaseDetailUseCase {

    override suspend fun invoke(phaseId: String): Result<PhaseDetail> {
        return service.fetchPhaseDetail(phaseId)
    }
}

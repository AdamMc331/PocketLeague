package com.adammcneilly.pocketleague.phase.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail

/**
 * Consumes a phase ID and requests detailed information about that phase.
 */
interface FetchPhaseDetailUseCase {

    /**
     * @see [FetchPhaseDetailUseCase]
     */
    suspend operator fun invoke(phaseId: String): Result<PhaseDetail>
}

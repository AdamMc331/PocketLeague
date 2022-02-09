package com.adammcneilly.pocketleague.phase.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.PhaseDetail

/**
 * The data contract to fetch information about a phase.
 */
interface PhaseService {
    /**
     * Fetch the [PhaseDetail] information for the supplied [phaseId].
     */
    suspend fun fetchPhaseDetail(phaseId: String): Result<PhaseDetail>
}

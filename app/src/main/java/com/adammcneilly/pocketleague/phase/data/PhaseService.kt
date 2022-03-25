package com.adammcneilly.pocketleague.phase.data

import com.adammcneilly.pocketleague.shared.core.models.PhaseDetail
import com.adammcneilly.pocketleague.shared.data.Result

/**
 * The data contract to fetch information about a phase.
 */
interface PhaseService {
    /**
     * Fetch the [PhaseDetail] information for the supplied [phaseId].
     */
    suspend fun fetchPhaseDetail(phaseId: String): Result<PhaseDetail>
}

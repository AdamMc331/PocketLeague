package com.adammcneilly.pocketleague.shared.data.phase

import com.adammcneilly.pocketleague.core.models.PhaseDetail
import com.adammcneilly.pocketleague.shared.data.Result
import kotlinx.coroutines.flow.Flow

/**
 * The data contract to fetch information about a phase.
 */
interface PhaseRepository {
    /**
     * Fetch the [PhaseDetail] information for the supplied [phaseId].
     */
    suspend fun fetchPhaseDetail(phaseId: String): Flow<Result<PhaseDetail>>
}

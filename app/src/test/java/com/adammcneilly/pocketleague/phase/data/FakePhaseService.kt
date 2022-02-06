package com.adammcneilly.pocketleague.phase.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail

class FakePhaseService : PhaseService {

    private var phaseDetailResponses: MutableMap<String, Result<PhaseDetail>> = mutableMapOf()

    fun mockPhaseDetailResponse(
        phaseId: String,
        response: Result<PhaseDetail>,
    ) {
        phaseDetailResponses[phaseId] = response
    }

    override suspend fun fetchPhaseDetail(phaseId: String): Result<PhaseDetail> {
        return phaseDetailResponses[phaseId]!!
    }
}

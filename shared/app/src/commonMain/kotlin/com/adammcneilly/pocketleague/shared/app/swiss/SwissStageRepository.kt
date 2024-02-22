package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.models.SwissTeamResult

interface SwissStageRepository {
    suspend fun fetchSwissStageResults(
        stageId: String,
    ): Result<List<SwissTeamResult>>
}

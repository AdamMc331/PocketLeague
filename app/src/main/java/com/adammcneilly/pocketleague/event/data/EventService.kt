package com.adammcneilly.pocketleague.event.data

import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage

interface EventService {
    suspend fun fetchSwissStage(
        eventName: String,
    ): SwissStage
}

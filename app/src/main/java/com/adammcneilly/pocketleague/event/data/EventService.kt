package com.adammcneilly.pocketleague.event.data

import com.adammcneilly.pocketleague.swiss.ui.SwissRound

interface EventService {
    suspend fun fetchSwissRounds(
        eventName: String,
    ): List<SwissRound>
}

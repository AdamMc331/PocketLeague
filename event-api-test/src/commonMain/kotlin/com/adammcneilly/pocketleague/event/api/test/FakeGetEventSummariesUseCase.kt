package com.adammcneilly.pocketleague.event.api.test

import com.adammcneilly.pocketleague.event.api.GetEventSummariesUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Concrete implementation of [GetEventSummariesUseCase] that will return mock data.
 */
class FakeGetEventSummariesUseCase : GetEventSummariesUseCase {
    val resultsForLeague: MutableMap<String, Flow<GetEventSummariesUseCase.Result>> = mutableMapOf()

    override fun invoke(
        leagueSlug: String,
    ): Flow<GetEventSummariesUseCase.Result> {
        return resultsForLeague[leagueSlug]!!
    }
}

package com.adammcneilly.pocketleague.event.api.test

import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Concrete implementation of [GetUpcomingEventSummariesUseCase] that will return mock data.
 */
class FakeGetUpcomingEventSummariesUseCase : GetUpcomingEventSummariesUseCase {
    val resultsForLeague: MutableMap<String, Flow<GetUpcomingEventSummariesUseCase.Result>> = mutableMapOf()

    override fun invoke(
        leagueSlug: String,
    ): Flow<GetUpcomingEventSummariesUseCase.Result> {
        return resultsForLeague[leagueSlug]!!
    }
}

package com.adammcneilly.pocketleague.event.implementation

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.test.testEventSummary
import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import com.adammcneilly.pocketleague.event.api.test.FakeEventRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetUpcomingEventSummariesUseCaseImplTest {
    private val fakeRepository = FakeEventRepository()
    private val useCase = GetUpcomingEventSummariesUseCaseImpl(
        repository = fakeRepository,
    )

    @Test
    fun mockSuccess() = runTest {
        val leagueSlug = "rlcs-2021-22-1"
        val events = listOf(testEventSummary)
        val repoResult = Result.Success(events)
        fakeRepository.upcomingEventSummariesByLeagues[leagueSlug] = flowOf(repoResult)

        val expectedResult = GetUpcomingEventSummariesUseCase.Result.Success(
            events = events,
        )

        useCase.invoke(leagueSlug)
            .test {
                assertEquals(expectedResult, awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
    }
}

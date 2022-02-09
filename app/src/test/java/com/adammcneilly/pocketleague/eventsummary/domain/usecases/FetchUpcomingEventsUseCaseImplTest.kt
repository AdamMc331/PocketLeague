package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.data.FakeEventService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchUpcomingEventsUseCaseImplTest {
    private val fakeEventService = FakeEventService()

    private val useCase = FetchUpcomingEventsUseCaseImpl(
        service = fakeEventService,
    )

    @Test
    fun invoke() = runTest {
        val leagueSlug = "rlcs-2021-22-1"

        val testEventSummary = EventSummary(
            id = "Event ID",
            eventName = "Event Name",
            tournamentName = "Tournament Name",
            tournamentImageUrl = "Tournament Image URL",
            startDateEpochSeconds = 123L,
            numEntrants = 1,
            isOnline = true,
        )

        val mockResult = Result.Success(listOf(testEventSummary))

        fakeEventService.mockUpcomingEventsForLeague(
            leagueSlug = leagueSlug,
            upcomingEvents = mockResult,
        )

        val actualResult = useCase.invoke()
        assertThat(actualResult).isEqualTo(mockResult)
    }
}

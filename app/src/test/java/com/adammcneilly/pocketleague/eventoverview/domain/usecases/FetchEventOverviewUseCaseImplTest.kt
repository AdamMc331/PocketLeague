package com.adammcneilly.pocketleague.eventoverview.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.data.FakeEventService
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.standings.domain.models.Standings
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.ZonedDateTime

class FetchEventOverviewUseCaseImplTest {
    private val fakeEventService = FakeEventService()
    private val useCase = FetchEventOverviewUseCaseImpl(
        service = fakeEventService,
    )

    @Test
    fun invoke() = runTest {
        val eventId = "Event ID"

        val testOverview = EventOverview(
            name = "Event Name",
            startDate = ZonedDateTime.now(),
            phases = listOf(),
            standings = Standings(
                placements = listOf(),
            ),
        )

        val mockResult = Result.Success(testOverview)

        fakeEventService.mockEventOverviewResponse(
            eventId = eventId,
            overview = mockResult,
        )

        val actualResult = useCase.invoke(eventId)
        assertThat(actualResult).isEqualTo(mockResult)
    }
}

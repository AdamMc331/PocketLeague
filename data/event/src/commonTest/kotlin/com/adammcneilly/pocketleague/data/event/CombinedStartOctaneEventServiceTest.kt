package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.data.event.test.FakeRemoteEventService
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CombinedStartOctaneEventServiceTest {

    private val octaneService = FakeRemoteEventService()
    private val startService = FakeRemoteEventService()

    private val combinedService = CombinedStartOctaneEventService(
        octaneGGEventService = octaneService,
        startGGEventService = startService,
    )

    @Test
    fun getUpcomingEventsShouldProxyToStartService() = runTest {
        combinedService.getUpcomingEvents()

        startService.verifyUpcomingEventsRequested(1)
        octaneService.verifyUpcomingEventsRequested(0)
    }

    @Test
    fun getEventParticipantsShouldProxyToStartService() = runTest {
        combinedService.getEventParticipants(TestModel.event.id)

        startService.verifyEventParticipantsRequested(1)
        octaneService.verifyEventParticipantsRequested(0)
    }

    @Test
    fun getOngoingEventsShouldProxyToStartService() = runTest {
        combinedService.getOngoingEvents()

        startService.verifyOngoingEventsRequested(1)
        octaneService.verifyOngoingEventsRequested(0)
    }

    @Test
    fun getEventByNameShouldThrow() = runTest {
        assertFailsWith(UnsupportedOperationException::class) {
            combinedService.getEventByName("Test")
        }
    }

    @Test
    fun getEventByIdShouldCombineBothServices() = runTest {
        val testId = TestModel.event.id
        val testStartName = "RLCS 2022-23 - Spring Invitational - Europe"
        val testOctaneName = "RLCS 2022-23 Spring Europe Regional 3"

        val testEvent = TestModel.event

        // We're mocking a real event from startgg by removing fields that their API does not support.
        val mockStartEvent = testEvent.copy(
            name = testStartName,
            tier = EventTier.Unknown,
            prize = null,
            mode = "",
            region = EventRegion.Unknown,
        )

        // Ensuring that the octane event includes the fields that start API is missing
        val mockOctaneEvent = testEvent.copy(
            name = testOctaneName,
            tier = EventTier.S,
            prize = Prize(10.0, "USD"),
            mode = "3",
            region = EventRegion.NA,
        )

        // The expected output has the start event name, and the fields that we added to mock octane event.
        val expectedEvent = testEvent.copy(
            name = testStartName,
            tier = EventTier.S,
            prize = Prize(10.0, "USD"),
            mode = "3",
            region = EventRegion.NA,
        )

        // For the start service, mock our event by ID.
        startService.eventsById[testId] = Result.success(mockStartEvent)

        // For the octane service, mock the event by name.
        octaneService.eventsByName[testOctaneName] = Result.success(mockOctaneEvent)

        assertEquals(expectedEvent, combinedService.getEventById(testId).getOrNull())
    }
}

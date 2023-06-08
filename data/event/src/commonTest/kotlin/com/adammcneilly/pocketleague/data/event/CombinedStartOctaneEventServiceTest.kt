package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.data.event.test.FakeRemoteEventService
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
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
    }
}

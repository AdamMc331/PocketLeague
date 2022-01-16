package com.adammcneilly.pocketleague.eventsummary.domain.state

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.utils.FakeDateTimeHelper
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FakeFetchUpcomingEventsUseCase
import com.adammcneilly.pocketleague.eventsummary.ui.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.eventsummary.ui.EventSummaryListViewState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.ZonedDateTime

class EventSummaryListStateMutatorTest {
    private val fetchUpcomingEventsUseCase = FakeFetchUpcomingEventsUseCase()
    private val dateTimeHelper = FakeDateTimeHelper()

    private val mutator = eventSummaryListStateMutator(
        scope = TestCoroutineScope(),
        fetchUpcomingEventsUseCase = fetchUpcomingEventsUseCase,
        dateTimeHelper = dateTimeHelper,
    )

    @Test
    fun fetchEventsSuccess() = runTest {
        // Inputs
        val fakeEvent = EventSummary(
            id = "1234",
            eventName = "Event Name",
            tournamentName = "Tournament Name",
            tournamentImageUrl = "Tournament Image URL",
            startDate = ZonedDateTime.now(),
            numEntrants = null,
            isOnline = true,
        )
        val fakeEventList = listOf(fakeEvent)
        val fakeEventListResult = Result.Success(fakeEventList)
        val fakeEventDateString = "Today"

        // Mocks
        fetchUpcomingEventsUseCase.mockResult = fakeEventListResult
        dateTimeHelper.mockEventDayStringForDate(fakeEvent.startDate, fakeEventDateString)

        // Expectations
        val loadingState = EventSummaryListViewState()
        val expectedEventDisplayModel = EventSummaryDisplayModel(
            eventId = fakeEvent.id,
            startDate = fakeEventDateString,
            tournamentName = fakeEvent.tournamentName,
            eventName = fakeEvent.eventName,
            subtitle = null,
            image = UIImage.Remote(fakeEvent.tournamentImageUrl)
        )
        val successState = EventSummaryListViewState(
            showLoading = false,
            events = listOf(expectedEventDisplayModel),
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventSummaryListAction.FetchUpcomingEvents)

                val first = awaitItem()
                assertThat(first).isEqualTo(loadingState)

                val second = awaitItem()
                assertThat(second).isEqualTo(successState)

                cancelAndIgnoreRemainingEvents()
            }
    }
}

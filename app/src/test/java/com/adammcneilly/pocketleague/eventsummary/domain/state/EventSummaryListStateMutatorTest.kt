package com.adammcneilly.pocketleague.eventsummary.domain.state

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
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
        val initialState = EventSummaryListViewState()
        val expectedEventDisplayModel = EventSummaryDisplayModel(
            eventId = fakeEvent.id,
            startDate = fakeEventDateString,
            tournamentName = fakeEvent.tournamentName,
            eventName = fakeEvent.eventName,
            subtitle = null,
            image = UIImage.Remote(fakeEvent.tournamentImageUrl)
        )
        val successState = initialState.copy(
            showLoading = false,
            events = listOf(expectedEventDisplayModel),
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventSummaryListAction.FetchUpcomingEvents)

                val expectedStates = listOf(initialState, successState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun fetchEventsFailure() = runTest {
        // Inputs
        val fakeEventListResult: Result<List<EventSummary>> = Result.Error(
            Throwable("Whoops"),
        )

        // Mocks
        fetchUpcomingEventsUseCase.mockResult = fakeEventListResult

        // Expectations
        val initialState = EventSummaryListViewState()
        val errorState = initialState.copy(
            showLoading = false,
            errorMessage = UIText.StringText(
                "Fetching upcoming events failed.",
            ),
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventSummaryListAction.FetchUpcomingEvents)

                val expectedStates = listOf(initialState, errorState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun selectAndNavigateToEvent() = runTest {
        // Inputs
        val fakeEventId = "1234"

        // Expectations
        val initialState = EventSummaryListViewState()
        val selectedState = initialState.copy(
            selectedEventId = fakeEventId,
        )
        val navigatedState = initialState.copy(
            selectedEventId = null,
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventSummaryListAction.SelectedEvent(fakeEventId))
                mutator.accept(EventSummaryListAction.NavigatedToEventOverview)

                val expectedStates = listOf(initialState, selectedState, navigatedState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }
}

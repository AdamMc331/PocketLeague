package com.adammcneilly.pocketleague.eventsummary.domain.state

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.test.testEventSummary
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.FakeDateTimeHelper
import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import com.adammcneilly.pocketleague.event.api.test.FakeGetUpcomingEventSummariesUseCase
import com.adammcneilly.pocketleague.eventsummary.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.eventsummary.EventSummaryListAction
import com.adammcneilly.pocketleague.eventsummary.EventSummaryListViewState
import com.adammcneilly.pocketleague.eventsummary.eventSummaryListStateMutator
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EventSummaryListStateMutatorTest {
    private val getUpcomingEventsUseCase = FakeGetUpcomingEventSummariesUseCase()
    private val dateTimeHelper = FakeDateTimeHelper()

    private val mutator = eventSummaryListStateMutator(
        scope = TestCoroutineScope(),
        getUpcomingEventsUseCase = getUpcomingEventsUseCase,
    )

    @Test
    fun fetchEventsSuccess() = runTest {
        val leagueSlug = "rlcs-2021-22-1"
        val fakeEvent = testEventSummary
        val events = listOf(fakeEvent)
        val useCaseResult = GetUpcomingEventSummariesUseCase.Result.Success(events)
        val fakeEventDateString = "TODO: Start Date"

        // Mocks
        getUpcomingEventsUseCase.resultsForLeague[leagueSlug] = flowOf(useCaseResult)
        dateTimeHelper.mockEventDayStringForDate(fakeEvent.startDateEpochSeconds, fakeEventDateString)

        // Expectations
        val initialState = EventSummaryListViewState()
        val expectedEventDisplayModel =
            EventSummaryDisplayModel(
                eventId = fakeEvent.id,
                startDate = fakeEventDateString,
                tournamentName = fakeEvent.tournamentName,
                eventName = fakeEvent.eventName,
                subtitle = "${fakeEvent.numEntrants} Teams",
                image = UIImage.Remote(fakeEvent.tournamentImageUrl)
            )
        val successState = initialState.copy(
            showLoading = false,
            events = listOf(expectedEventDisplayModel),
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventSummaryListAction.FetchUpcomingEvents(leagueSlug))

                val expectedStates = listOf(initialState, successState)

                expectedStates.forEach { state ->
                    assertThat(awaitItem()).isEqualTo(state)
                }

                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun fetchEventsFailure() = runTest {
        val leagueSlug = "rlcs-2021-22-1"
        val useCaseResult = GetUpcomingEventSummariesUseCase.Result.Error("Whoops")

        // Mocks
        getUpcomingEventsUseCase.resultsForLeague[leagueSlug] = flowOf(useCaseResult)

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
                mutator.accept(EventSummaryListAction.FetchUpcomingEvents(leagueSlug))

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

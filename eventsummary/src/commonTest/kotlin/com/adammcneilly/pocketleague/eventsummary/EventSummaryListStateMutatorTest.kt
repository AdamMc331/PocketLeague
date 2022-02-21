package com.adammcneilly.pocketleague.eventsummary

//class EventSummaryListStateMutatorTest {
//    private val getUpcomingEventsUseCase = FakeGetUpcomingEventSummariesUseCase()
//
//    private val mutator = eventSummaryListStateMutator(
//        scope = TestScope(),
//        getUpcomingEventsUseCase = getUpcomingEventsUseCase,
//    )
//
//    @Test
//    fun fetchEventsSuccess() = runTest {
//        val leagueSlug = "rlcs-2021-22-1"
//        val fakeEvent = testEventSummary
//        val events = listOf(fakeEvent)
//        val useCaseResult = GetUpcomingEventSummariesUseCase.Result.Success(events)
//        val fakeEventDateString = "TODO: Start Date"
//
//        // Mocks
//        getUpcomingEventsUseCase.resultsForLeague[leagueSlug] = flowOf(useCaseResult)
//
//        // Expectations
//        val initialState = EventSummaryListViewState()
//        val expectedEventDisplayModel =
//            EventSummaryDisplayModel(
//                eventId = fakeEvent.id,
//                startDate = fakeEventDateString,
//                tournamentName = fakeEvent.tournamentName,
//                eventName = fakeEvent.eventName,
//                subtitle = "${fakeEvent.numEntrants} Teams",
//                image = UIImage.Remote(fakeEvent.tournamentImageUrl)
//            )
//        val successState = initialState.copy(
//            showLoading = false,
//            events = listOf(expectedEventDisplayModel),
//        )
//
//        // Test
//        mutator.state
//            .test {
//                mutator.accept(EventSummaryListAction.FetchUpcomingEvents(leagueSlug))
//
//                val expectedStates = listOf(initialState, successState)
//
//                expectedStates.forEach { state ->
//                    assertEquals(state, awaitItem())
//                }
//
//                cancelAndIgnoreRemainingEvents()
//            }
//    }
//
//    @Test
//    fun fetchEventsFailure() = runTest {
//        val leagueSlug = "rlcs-2021-22-1"
//        val useCaseResult = GetUpcomingEventSummariesUseCase.Result.Error("Whoops")
//
//        // Mocks
//        getUpcomingEventsUseCase.resultsForLeague[leagueSlug] = flowOf(useCaseResult)
//
//        // Expectations
//        val initialState = EventSummaryListViewState()
//        val errorState = initialState.copy(
//            showLoading = false,
//            errorMessage = UIText.StringText(
//                "Fetching upcoming events failed.",
//            ),
//        )
//
//        // Test
//        mutator.state
//            .test {
//                mutator.accept(EventSummaryListAction.FetchUpcomingEvents(leagueSlug))
//
//                val expectedStates = listOf(initialState, errorState)
//
//                expectedStates.forEach { state ->
//                    assertEquals(state, awaitItem())
//                }
//
//                cancelAndIgnoreRemainingEvents()
//            }
//    }
//
//    @Test
//    fun selectAndNavigateToEvent() = runTest {
//        // Inputs
//        val fakeEventId = "1234"
//
//        // Expectations
//        val initialState = EventSummaryListViewState()
//        val selectedState = initialState.copy(
//            selectedEventId = fakeEventId,
//        )
//        val navigatedState = initialState.copy(
//            selectedEventId = null,
//        )
//
//        // Test
//        mutator.state
//            .test {
//                mutator.accept(EventSummaryListAction.SelectedEvent(fakeEventId))
//                mutator.accept(EventSummaryListAction.NavigatedToEventOverview)
//
//                val expectedStates = listOf(initialState, selectedState, navigatedState)
//
//                expectedStates.forEach { state ->
//                    assertEquals(state, awaitItem())
//                }
//
//                cancelAndIgnoreRemainingEvents()
//            }
//    }
//}

package com.adammcneilly.pocketleague.eventoverview.domain.state

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.FakeDateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FakeFetchEventOverviewUseCase
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewDisplayModel
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewViewState
import com.adammcneilly.pocketleague.models.BracketType
import com.adammcneilly.pocketleague.models.EventOverview
import com.adammcneilly.pocketleague.models.PhaseOverview
import com.adammcneilly.pocketleague.models.Player
import com.adammcneilly.pocketleague.models.Standings
import com.adammcneilly.pocketleague.models.StandingsPlacement
import com.adammcneilly.pocketleague.models.Team
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.ZonedDateTime

class EventOverviewStateMutatorTest {
    private val fetchEventOverviewUseCase = FakeFetchEventOverviewUseCase()
    private val dateTimeHelper = FakeDateTimeHelper()

    private val mutator = eventOverviewStateMutator(
        scope = TestCoroutineScope(),
        fetchEventOverviewUseCase = fetchEventOverviewUseCase,
        dateTimeHelper = dateTimeHelper,
    )

    @Suppress("LongMethod")
    @Test
    fun fetchEventOverviewSuccess() = runTest {
        // Inputs
        val fakeEventId = "1234"
        val fakeEvent = EventOverview(
            name = "Event Name",
            startDateEpochSeconds = ZonedDateTime.now(),
            phases = listOf(
                PhaseOverview(
                    id = "Phase ID",
                    groupId = "Group ID",
                    numPools = 0,
                    numEntrants = 0,
                    name = "Phase Name",
                    phaseOrder = 0,
                    bracketType = BracketType.UNKNOWN,
                ),
            ),
            standings = Standings(
                placements = listOf(
                    StandingsPlacement(
                        placement = 0,
                        team = Team(
                            name = "Team Name",
                            lightThemeLogoImageUrl = "Light",
                            darkThemeLogoImageUrl = "Dark",
                            roster = listOf(
                                Player(
                                    countryCode = "US",
                                    gamerTag = "AdamMc331",
                                    realName = "Adam",
                                    notes = null,
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        )
        val fakeEventOverviewResult = Result.Success(fakeEvent)
        val fakeEventDateString = "Today"

        // Mocks
        fetchEventOverviewUseCase.mockResultForEvent(fakeEventId, fakeEventOverviewResult)
        dateTimeHelper.mockEventDayStringForDate(fakeEvent.startDateEpochSeconds, fakeEventDateString)

        // Expectations
        val initialState = EventOverviewViewState()
        val expectedEventDisplayModel = EventOverviewDisplayModel(
            eventName = fakeEvent.name,
            startDate = fakeEventDateString,
            phases = listOf(
                PhaseDisplayModel(
                    phaseName = "Phase Name",
                    numPools = "0",
                    bracketType = "Unknown",
                    numEntrants = "0",
                    onClick = {},
                ),
            ),
            standings = StandingsDisplayModel(
                placements = listOf(
                    StandingsPlacementDisplayModel(
                        placement = "0",
                        teamName = "Team Name",
                        roster = "AdamMc331",
                        teamLogo = UIImage.Remote("Light"),
                    ),
                ),
            ),
        )
        val successState = initialState.copy(
            showLoading = false,
            event = expectedEventDisplayModel,
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventOverviewAction.FetchEventOverview(fakeEventId))

                val expectedStates = listOf(initialState, successState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun fetchEventOverviewFailure() = runTest {
        // Inputs
        val fakeEventId = "1234"
        val fakeEventOverviewResult: Result<EventOverview> = Result.Error(
            Throwable("Whoops"),
        )

        // Mocks
        fetchEventOverviewUseCase.mockResultForEvent(
            fakeEventId,
            fakeEventOverviewResult,
        )

        // Expectations
        val initialState = EventOverviewViewState()
        val errorState = initialState.copy(
            showLoading = false,
            errorMessage = UIText.StringText(
                "Fetching event overview failed.",
            ),
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventOverviewAction.FetchEventOverview(fakeEventId))

                val expectedStates = listOf(initialState, errorState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun selectAndNavigateToPhase() = runTest {
        // Inputs
        val fakePhaseId = "1234"

        // Expectations
        val initialState = EventOverviewViewState()
        val selectedState = initialState.copy(
            selectedPhaseId = fakePhaseId,
        )
        val navigatedState = initialState.copy(
            selectedPhaseId = null,
        )

        // Test
        mutator.state
            .test {
                mutator.accept(EventOverviewAction.SelectPhase(fakePhaseId))
                mutator.accept(EventOverviewAction.NavigatedToPhaseDetail)

                val expectedStates = listOf(initialState, selectedState, navigatedState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }
}

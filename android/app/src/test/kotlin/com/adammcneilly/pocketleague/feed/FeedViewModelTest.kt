package com.adammcneilly.pocketleague.feed

import com.adammcneilly.pocketleague.CoroutinesTestRule
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.matchBlueWinner
import com.adammcneilly.pocketleague.data.event.test.FakeEventRepository
import com.adammcneilly.pocketleague.data.match.test.FakeMatchRepository
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import kotlin.test.Test

class FeedViewModelTest {
    private val eventRepository = FakeEventRepository()
    private val matchRepository = FakeMatchRepository()

    private lateinit var viewModel: FeedViewModel

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun observeData() {
        val testMatch = TestModel.matchBlueWinner
        val testUpcomingEvent = TestModel.event.copy(
            name = "Upcoming",
        )
        val testOngoingEvent = TestModel.event.copy(
            name = "Ongoing",
        )

        eventRepository.ongoingEvents = listOf(testOngoingEvent)
        eventRepository.upcomingEvents = listOf(testUpcomingEvent)
        matchRepository.pastWeeksMatches = listOf(testMatch)

        val expectedViewState = FeedViewState(
            ongoingEvents = listOf(testOngoingEvent.toSummaryDisplayModel()),
            upcomingEvents = listOf(testUpcomingEvent.toSummaryDisplayModel()),
            recentMatches = listOf(testMatch.toDetailDisplayModel()),
        )

        val viewModel = FeedViewModel(
            eventRepository = eventRepository,
            matchRepository = matchRepository,
        )

        assertThat(viewModel.state.value).isEqualTo(expectedViewState)
    }
}

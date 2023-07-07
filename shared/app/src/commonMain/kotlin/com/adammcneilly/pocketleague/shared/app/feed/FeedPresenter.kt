package com.adammcneilly.pocketleague.shared.app.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.launch

private const val PLACEHOLDER_LIST_COUNT = 3

/**
 * State management container for the [FeedScreen].
 */
class FeedPresenter(
    private val navigator: Navigator,
) : Presenter<FeedScreen.State> {

    @Composable
    override fun present(): FeedScreen.State {
        var matches by remember {
            val initialList = List(PLACEHOLDER_LIST_COUNT) {
                MatchDetailDisplayModel.placeholder
            }
            mutableStateOf(initialList)
        }

        var ongoingEvents by remember {
            mutableStateOf(EventGroupDisplayModel.placeholder)
        }

        var upcomingEvents by remember {
            mutableStateOf(EventGroupDisplayModel.placeholder)
        }

        LaunchedEffect(Unit) {
            launch {
                matches = OctaneGGMatchService()
                    .getPastWeeksMatches()
                    .getOrNull()
                    ?.sortedByDescending(Match::dateUTC)
                    ?.map { it.toDetailDisplayModel() }
                    .orEmpty()
            }

            launch {
                ongoingEvents = OctaneGGEventService()
                    .getOngoingEvents()
                    .getOrNull()
                    ?.sortedBy(Event::startDateUTC)
                    ?.map(Event::toSummaryDisplayModel)
                    ?.let(EventGroupDisplayModel.Companion::mapFromEventList)
                    .orEmpty()
            }

            launch {
                upcomingEvents = OctaneGGEventService()
                    .getUpcomingEvents()
                    .getOrNull()
                    ?.sortedBy(Event::startDateUTC)
                    ?.map(Event::toSummaryDisplayModel)
                    ?.let(EventGroupDisplayModel.Companion::mapFromEventList)
                    .orEmpty()
            }
        }

        return FeedScreen.State(
            recentMatches = matches,
            ongoingEvents = ongoingEvents,
            upcomingEvents = upcomingEvents,
        ) { event ->
            when (event) {
                is FeedScreen.Event.EventClicked -> {
                    // Handle event clicked
                }

                is FeedScreen.Event.MatchClicked -> {
                    // Handle match clicked
                }
            }
        }
    }
}

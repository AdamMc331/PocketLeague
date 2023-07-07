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

/**
 * State management container for the [FeedScreen].
 */
class FeedPresenter(
    private val navigator: Navigator,
) : Presenter<FeedScreen.State> {

    @Composable
    override fun present(): FeedScreen.State {
        var matches by remember {
            mutableStateOf(emptyList<MatchDetailDisplayModel>())
        }

        var ongoingEvents by remember {
            mutableStateOf(emptyList<EventGroupDisplayModel>())
        }

        var upcomingEvents by remember {
            mutableStateOf(emptyList<EventGroupDisplayModel>())
        }

        LaunchedEffect(Unit) {
            matches = OctaneGGMatchService()
                .getPastWeeksMatches()
                .getOrNull()
                ?.sortedByDescending(Match::dateUTC)
                ?.map { it.toDetailDisplayModel() }
                .orEmpty()

            ongoingEvents = OctaneGGEventService()
                .getOngoingEvents()
                .getOrNull()
                ?.sortedBy(Event::startDateUTC)
                ?.map(Event::toSummaryDisplayModel)
                ?.let(EventGroupDisplayModel.Companion::mapFromEventList)
                .orEmpty()

            upcomingEvents = OctaneGGEventService()
                .getUpcomingEvents()
                .getOrNull()
                ?.sortedBy(Event::startDateUTC)
                ?.map(Event::toSummaryDisplayModel)
                ?.let(EventGroupDisplayModel.Companion::mapFromEventList)
                .orEmpty()
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

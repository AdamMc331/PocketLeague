package com.adammcneilly.pocketleague.shared.app.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.EventRepository
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.shared.app.match.MatchDetailScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private const val PLACEHOLDER_LIST_COUNT = 3

/**
 * State management container for the [FeedScreen].
 */
class FeedPresenter(
    private val getPastWeeksMatchesUseCase: GetPastWeeksMatchesUseCase,
    private val eventRepository: EventRepository,
    private val timeProvider: TimeProvider,
    private val navigator: Navigator,
) : Presenter<FeedScreen.State> {

    @Composable
    override fun present(): FeedScreen.State {
        var matches by rememberSaveable {
            val initialList = List(PLACEHOLDER_LIST_COUNT) {
                MatchDetailDisplayModel.placeholder
            }
            mutableStateOf(initialList)
        }

        var ongoingEvents by rememberSaveable {
            mutableStateOf(EventGroupDisplayModel.placeholder)
        }

        var upcomingEvents by rememberSaveable {
            mutableStateOf(EventGroupDisplayModel.placeholder)
        }

        LaunchedEffect(Unit) {
            observePastWeeksMatches()
                .onEach { matchList ->
                    matches = matchList
                }
                .launchIn(this)

            observeOngoingEvents()
                .onEach { eventList ->
                    ongoingEvents = eventList
                }
                .launchIn(this)

            observeUpcomingEvents()
                .onEach { eventList ->
                    upcomingEvents = eventList
                }
                .launchIn(this)
        }

        return FeedScreen.State(
            recentMatches = matches,
            ongoingEvents = ongoingEvents,
            upcomingEvents = upcomingEvents,
        ) { event ->
            when (event) {
                is FeedScreen.Event.EventClicked -> {
                    // This will probably need to be fixed when we eventually move feed
                    // into its own feature module.
                    navigator.goTo(EventDetailScreen(event.eventId.id))
                }

                is FeedScreen.Event.MatchClicked -> {
                    navigator.goTo(MatchDetailScreen(event.matchId.id))
                }
            }
        }
    }

    private fun observePastWeeksMatches() = getPastWeeksMatchesUseCase
        .invoke()
        .map { matchList ->
            matchList.map { match ->
                match.toDetailDisplayModel(timeProvider)
            }
        }

    private fun observeOngoingEvents(): Flow<List<EventGroupDisplayModel>> {
        val request = EventListRequest.OnDate(
            dateUtc = timeProvider.now(),
        )

        return eventRepository
            .stream(request)
            .map { eventList ->
                eventList.map(Event::toSummaryDisplayModel)
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)
    }

    private fun observeUpcomingEvents(): Flow<List<EventGroupDisplayModel>> {
        val request = EventListRequest.AfterDate(
            dateUtc = timeProvider.now(),
        )

        return eventRepository
            .stream(request)
            .map { eventList ->
                eventList.map(Event::toSummaryDisplayModel)
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)
    }
}

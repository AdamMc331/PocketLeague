@file:Suppress("TooManyFunctions")

package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Creates a [stateFlowMutator] which will consume [EventSummaryListAction] entities and map them
 * to the correct [EventSummaryListViewState].
 */
fun eventSummaryListStateMutator(
    eventRepository: EventRepository,
    scope: CoroutineScope,
) = stateFlowMutator<EventSummaryListAction, EventSummaryListViewState>(
    scope = scope,
    initialState = EventSummaryListViewState(),
    actionTransform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is EventSummaryListAction.FetchUpcomingEvents -> action.flow.fetchEventMutations(
                    eventRepository = eventRepository,
                )
                is EventSummaryListAction.NavigatedToEventOverview -> action.flow.clearEventMutations()
                is EventSummaryListAction.SelectedEvent -> action.flow.selectEventMutations()
            }
        }
    }
)

private fun Flow<EventSummaryListAction.FetchUpcomingEvents>.fetchEventMutations(
    eventRepository: EventRepository,
): Flow<Mutation<EventSummaryListViewState>> {
    return this.flatMapLatest { action ->
        eventRepository
            .fetchUpcomingEventSummaries(action.leagueSlug)
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        successMutation(
                            events = result.data,
                        )
                    }
                    is Result.Error -> {
                        errorMutation()
                    }
                }
            }
            .onStart {
                emit(loadingMutation())
            }
    }
}

private fun errorMutation() = Mutation<EventSummaryListViewState> {
    copy(
        showLoading = false,
        errorMessage = UIText.StringText(
            "Fetching upcoming events failed.",
        ),
    )
}

private fun successMutation(
    events: List<EventSummary>,
) = Mutation<EventSummaryListViewState> {
    copy(
        showLoading = false,
        events = events.map { event ->
            event.toSummaryDisplayModel()
        }
    )
}

private fun loadingMutation() = Mutation<EventSummaryListViewState> {
    copy(
        showLoading = true,
    )
}

private fun Flow<EventSummaryListAction.NavigatedToEventOverview>.clearEventMutations():
    Flow<Mutation<EventSummaryListViewState>> {

    return this.map {
        Mutation {
            copy(
                selectedEventId = null,
            )
        }
    }
}

private fun Flow<EventSummaryListAction.SelectedEvent>.selectEventMutations():
    Flow<Mutation<EventSummaryListViewState>> {

    return this.map { action ->
        Mutation {
            copy(
                selectedEventId = action.eventId,
            )
        }
    }
}

/**
 * Converts an [EventSummary] domain object to a user friendly [EventSummaryDisplayModel].
 */
private fun EventSummary.toSummaryDisplayModel(): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = this.id,
        startDate = "TODO: Start Date",
        tournamentName = this.tournamentName,
        eventName = this.eventName,
        subtitle = this.buildSubtitle(),
        image = UIImage.Remote(
            imageUrl = this.tournamentImageUrl,
        ),
    )
}

/**
 * Generates a user friendly subtitle for an [EventSummary] intended to be passed to an [EventSummaryDisplayModel].
 */
private fun EventSummary.buildSubtitle(): String? {
    return this.numEntrants?.let { numEntrants ->
        "$numEntrants Teams"
    }
}

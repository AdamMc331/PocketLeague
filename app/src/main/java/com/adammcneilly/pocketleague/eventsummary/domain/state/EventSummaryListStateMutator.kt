package com.adammcneilly.pocketleague.eventsummary.domain.state

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCase
import com.adammcneilly.pocketleague.eventsummary.ui.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.eventsummary.ui.EventSummaryListViewState
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Creates a [stateFlowMutator] which will consume [EventSummaryListAction] entities and map them
 * to the correct [EventSummaryListViewState].
 */
fun eventSummaryListStateMutator(
    scope: CoroutineScope,
    fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCase,
    dateTimeHelper: DateTimeHelper,
) = stateFlowMutator<EventSummaryListAction, EventSummaryListViewState>(
    scope = scope,
    initialState = EventSummaryListViewState(),
    transform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is EventSummaryListAction.FetchUpcomingEvents -> action.flow.fetchEventMutations(
                    fetchUpcomingEventsUseCase = fetchUpcomingEventsUseCase,
                    dateTimeHelper = dateTimeHelper,
                )
                is EventSummaryListAction.NavigatedToEventOverview -> action.flow.clearEventMutations()
                is EventSummaryListAction.SelectedEvent -> action.flow.selectEventMutations()
            }
        }
    }
)

private fun Flow<EventSummaryListAction.FetchUpcomingEvents>.fetchEventMutations(
    fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCase,
    dateTimeHelper: DateTimeHelper,
): Flow<Mutation<EventSummaryListViewState>> {
    return this.flatMapLatest {
        flow {
            emitLoading()

            val result = fetchUpcomingEventsUseCase.invoke()

            when (result) {
                is Result.Success -> {
                    emitSuccess(
                        events = result.data,
                        dateTimeHelper = dateTimeHelper,
                    )
                }
                is Result.Error -> {
                    emitError()
                }
            }
        }
    }
}

private suspend fun FlowCollector<Mutation<EventSummaryListViewState>>.emitError() = this.emit(
    Mutation {
        copy(
            errorMessage = UIText.StringText(
                "Fetching upcoming events failed.",
            ),
        )
    }
)

private suspend fun FlowCollector<Mutation<EventSummaryListViewState>>.emitSuccess(
    events: List<EventSummary>,
    dateTimeHelper: DateTimeHelper,
) = this.emit(
    Mutation {
        copy(
            events = events.map { event ->
                event.toSummaryDisplayModel(
                    dateTimeHelper = dateTimeHelper,
                )
            }
        )
    }
)

private suspend fun FlowCollector<Mutation<EventSummaryListViewState>>.emitLoading() = this.emit(
    Mutation {
        copy(
            showLoading = true,
        )
    }
)

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
private fun EventSummary.toSummaryDisplayModel(
    dateTimeHelper: DateTimeHelper,
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = this.id,
        startDate = dateTimeHelper.getEventDayString(this.startDate),
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

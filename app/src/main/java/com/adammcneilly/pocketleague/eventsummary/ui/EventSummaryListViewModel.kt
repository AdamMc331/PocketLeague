package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventsummary.domain.EventSummaryListAction
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCase
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * A state management container for the [EventSummaryListScreen].
 */
@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    private val fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCase,
    private val dateTimeHelper: DateTimeHelper,
) : ViewModel() {

    private val mutator = stateFlowMutator<EventSummaryListAction, EventSummaryListViewState>(
        scope = viewModelScope,
        initialState = EventSummaryListViewState(),
        transform = { actions ->
            actions.toMutationStream {
                when (val action = type()) {
                    is EventSummaryListAction.FetchUpcomingEvents ->
                        action.flow
                            .flatMapLatest {
                                flow<Mutation<EventSummaryListViewState>> {
                                    emit(
                                        Mutation {
                                            copy(
                                                showLoading = true,
                                            )
                                        }
                                    )

                                    val result = fetchUpcomingEventsUseCase.invoke()

                                    when (result) {
                                        is Result.Success -> {
                                            emit(
                                                Mutation {
                                                    this.copy(
                                                        events = mapEventsToDisplayModel(result.data),
                                                    )
                                                }
                                            )
                                        }
                                        is Result.Error -> {
                                            emit(
                                                Mutation {
                                                    copy(
                                                        errorMessage = UIText.StringText("Fetching upcoming events failed."),
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                    is EventSummaryListAction.NavigatedToEventOverview ->
                        action.flow
                            .map {
                                Mutation {
                                    copy(
                                        selectedEvent = null,
                                    )
                                }
                            }
                    is EventSummaryListAction.SelectedEvent ->
                        action.flow
                            .map {
                                Mutation {
                                    copy(
                                        selectedEvent = it.event,
                                    )
                                }
                            }
                }
            }
        }
    )

    val viewState = mutator.state

    init {
        val fetchAction = EventSummaryListAction.FetchUpcomingEvents
        mutator.accept(fetchAction)
    }

    private fun mapEventsToDisplayModel(
        events: List<EventSummary>,
    ): List<EventSummaryDisplayModel> {
        return events.map { event ->
            event.toSummaryDisplayModel(
                dateTimeHelper = dateTimeHelper,
                onClick = {
                    val action = EventSummaryListAction.SelectedEvent(event = event)

                    mutator.accept(action)
                },
            )
        }
    }

    /**
     * When we navigate to an event overview, we should clear our selected event so that we don't
     * continue to show it.
     */
    fun navigatedToEventOverview() {
        val action = EventSummaryListAction.NavigatedToEventOverview

        mutator.accept(action)
    }
}

/**
 * Converts an [EventSummary] domain object to a user friendly [EventSummaryDisplayModel].
 */
private fun EventSummary.toSummaryDisplayModel(
    dateTimeHelper: DateTimeHelper,
    onClick: () -> Unit,
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        startDate = dateTimeHelper.getEventDayString(this.startDate),
        tournamentName = this.tournamentName,
        eventName = this.eventName,
        subtitle = this.buildSubtitle(),
        image = UIImage.Remote(
            imageUrl = this.tournamentImageUrl,
        ),
        onClick = onClick,
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

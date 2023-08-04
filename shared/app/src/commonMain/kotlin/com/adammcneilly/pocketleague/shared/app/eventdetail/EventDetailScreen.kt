package com.adammcneilly.pocketleague.shared.app.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.feature.CommonParcelize
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * The screen that shows detailed information about the event with the supplied [eventId].
 */
@CommonParcelize
data class EventDetailScreen(
    val eventId: String,
) : Screen {

    /**
     * Representation of the UI state for the [EventDetailScreen].
     */
    data class State(
        val event: EventDetailDisplayModel,
        val selectedStageId: EventStage.Id,
        val matchesForSelectedStage: List<MatchDetailDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * An enumeration of UI events that can happen on the [EventDetailScreen].
     */
    sealed interface Event : CircuitUiEvent {
        data class StageSelected(
            val stageId: EventStage.Id,
        ) : Event
    }

    /**
     * Factory to create the UI for the [EventDetailScreen].
     */
    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                is EventDetailScreen -> {
                    ui<State> { state, modifier ->
                        EventDetailContent(
                            event = state.event,
                            selectedStage = state.selectedStageId,
                            matchesForSelectedStage = state.matchesForSelectedStage,
                            onStageSelected = { stageId ->
                                val uiEvent = Event.StageSelected(stageId)
                                state.eventSink.invoke(uiEvent)
                            },
                            modifier = modifier,
                        )
                    }
                }

                else -> null
            }
        }
    }

    /**
     * Factory to create an [EventDetailPresenter].
     */
    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val eventRepository: EventRepository by inject()
        private val matchRepository: MatchRepository by inject()

        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                is EventDetailScreen -> {
                    EventDetailPresenter(
                        eventId = com.adammcneilly.pocketleague.core.models.Event.Id(screen.eventId),
                        eventRepository = eventRepository,
                        matchRepository = matchRepository,
                    )
                }

                else -> null
            }
        }
    }
}

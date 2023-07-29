package com.adammcneilly.pocketleague.shared.app.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.feature.CommonParcelize
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.EventRepository
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

@CommonParcelize
data class EventDetailScreen(
    val eventId: String,
) : Screen {

    data class State(
        val event: EventDetailDisplayModel,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent

    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                is EventDetailScreen -> {
                    ui<State> { state, modifier ->
                        EventDetailContent(
                            modifier = modifier,
                        )
                    }
                }

                else -> null
            }
        }
    }

    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val eventRepository: EventRepository by inject()

        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                is EventDetailScreen -> {
                    EventDetailPresenter(
                        eventId = com.adammcneilly.pocketleague.core.models.Event.Id(screen.eventId),
                        eventRepository = eventRepository,
                    )
                }

                else -> null
            }
        }
    }
}

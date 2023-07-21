package com.adammcneilly.pocketleague.shared.app.stage

import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.app.CommonParcelize
import com.adammcneilly.pocketleague.shared.ui.brackets.swiss.SwissStageTeamResultList
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

/**
 * A [Screen] implementation to show details about the swiss stage
 * of some [eventId].
 */
@CommonParcelize
data class SwissStageDetailScreen(
    val eventId: String,
    val stageId: String,
) : Screen {
    data class State(
        val teamResults: List<SwissStageTeamResultDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {

        data class TeamResultClicked(
            val teamResult: SwissStageTeamResultDisplayModel,
        ) : Event
    }

    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                is SwissStageDetailScreen -> {
                    ui<State> { state, modifier ->
                        SwissStageTeamResultList(
                            teamResults = state.teamResults,
                            modifier = modifier,
                        )
                    }
                }
                else -> null
            }
        }
    }

    class PresenterFactory(
        private val eventRepository: EventRepository,
    ) : Presenter.Factory {
        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                is SwissStageDetailScreen -> SwissStageDetailPresenter(
                    eventId = screen.eventId,
                    stageId = screen.stageId,
                    eventRepository = eventRepository,
                )
                else -> null
            }
        }
    }
}

package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.feature.CommonParcelize
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@CommonParcelize
data class SwissOverviewScreen(
    val stageId: String,
) : Screen {
    data class State(
        val results: List<SwissTeamResultDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent

    object UiFactory : Ui.Factory {
        override fun create(
            screen: Screen,
            context: CircuitContext,
        ): Ui<*>? {
            return when (screen) {
                is SwissOverviewScreen -> {
                    ui<State> { state, modifier ->
//                        SwissOverviewContent(
//                            state = state,
//                            modifier = modifier,
//                        )
                        SwissDetailContent(modifier)
                    }
                }

                else -> null
            }
        }
    }

    class PresenterFactory : Presenter.Factory, KoinComponent {
        private val swissStageRepository: SwissStageRepository by inject()

        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is SwissOverviewScreen -> {
                    SwissOverviewPresenter(
                        stageId = screen.stageId,
                        stageRepository = swissStageRepository,
                    )
                }

                else -> null
            }
        }
    }
}

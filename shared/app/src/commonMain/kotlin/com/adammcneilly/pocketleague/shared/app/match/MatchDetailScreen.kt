package com.adammcneilly.pocketleague.shared.app.match

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.app.CommonParcelize
import com.adammcneilly.pocketleague.shared.ui.match.MatchDetailContent
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

/**
 * Shows detailed match information for a match with a given [matchId].
 */
@CommonParcelize
data class MatchDetailScreen(
    val matchId: String,
) : Screen {
    /**
     * UI state for the [MatchDetailScreen].
     */
    data class State(
        val displayModel: MatchDetailDisplayModel?,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * Any events that can be triggered by the [MatchDetailScreen].
     */
    sealed interface Event : CircuitUiEvent

    /**
     * Factory to create the compose UI for the [MatchDetailScreen].
     */
    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                is MatchDetailScreen -> {
                    ui<State> { state, modifier ->
                        MatchDetailContent(
                            matchId = state.displayModel?.matchId.orEmpty(),
                            modifier = modifier,
                        )
                    }
                }
                else -> null
            }
        }
    }

    /**
     * Factory to create the [MatchDetailPresenter] for the [MatchDetailScreen].
     */
    object PresenterFactory : Presenter.Factory {
        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                is MatchDetailScreen -> MatchDetailPresenter(
                    matchId = screen.matchId,
                )
                else -> null
            }
        }
    }
}

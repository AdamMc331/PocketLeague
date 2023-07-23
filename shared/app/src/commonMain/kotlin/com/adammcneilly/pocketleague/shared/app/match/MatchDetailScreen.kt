package com.adammcneilly.pocketleague.shared.app.match

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchRepository
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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Shows detailed match information for a match with a given [matchId].
 *
 * Need to use a string until we can upgrade Kotlin: https://stackoverflow.com/a/75857372/3131147
 */
@CommonParcelize
data class MatchDetailScreen(
    val matchId: String,
) : Screen {
    /**
     * UI state for the [MatchDetailScreen].
     */
    data class State(
        val match: MatchDetailDisplayModel,
        val games: List<GameDetailDisplayModel>,
        val selectedGame: GameDetailDisplayModel?,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * Any events that can be triggered by the [MatchDetailScreen].
     */
    sealed interface Event : CircuitUiEvent {

        /**
         * Triggered when a user clicks on a specific game from the list.
         */
        data class GameSelected(
            val game: GameDetailDisplayModel,
        ) : Event

        /**
         * Triggered whenever the user dismisses the currently selected game dialog.
         */
        object SelectedGameDismissed : Event
    }

    /**
     * Factory to create the compose UI for the [MatchDetailScreen].
     */
    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                is MatchDetailScreen -> {
                    ui<State> { state, modifier ->
                        MatchDetailContent(
                            match = state.match,
                            games = state.games,
                            selectedGame = state.selectedGame,
                            onSelectedGameDismissed = {
                                state.eventSink.invoke(Event.SelectedGameDismissed)
                            },
                            onGameClicked = { game ->
                                state.eventSink.invoke(Event.GameSelected(game))
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
     * Factory to create the [MatchDetailPresenter] for the [MatchDetailScreen].
     */
    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val gameService: GameService by inject()
        private val matchRepository: MatchRepository by inject()

        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                is MatchDetailScreen -> MatchDetailPresenter(
                    matchId = Match.Id(screen.matchId),
                    gameService = gameService,
                    matchRepository = matchRepository,
                )

                else -> null
            }
        }
    }
}

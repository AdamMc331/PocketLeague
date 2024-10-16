package com.adammcneilly.pocketleague.feature.teamdetail

import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.Parcelize
import com.adammcneilly.pocketleague.data.player.PlayerRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository
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

/**
 * Screen component for the team detail feature.
 */
@Parcelize
data class TeamDetailScreen(
    val teamId: String,
) : Screen {
    /**
     * Representation of the UI state for the [TeamDetailScreen].
     */
    data class State(
        val team: TeamOverviewDisplayModel,
        val roster: List<PlayerDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * An enumeration of UI events that can occur on the [TeamDetailScreen].
     */
    sealed interface Event : CircuitUiEvent

    /**
     * Factory to create the UI for the [TeamDetailScreen].
     */
    object UiFactory : Ui.Factory {
        override fun create(
            screen: Screen,
            context: CircuitContext,
        ): Ui<*>? {
            return when (screen) {
                is TeamDetailScreen -> {
                    ui<State> { state, modifier ->
                        TeamDetailContent(
                            state = state,
                            modifier = modifier,
                        )
                    }
                }

                else -> null
            }
        }
    }

    /**
     * Factory to create a [TeamDetailPresenter].
     */
    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val teamRepository: TeamRepository by inject()
        private val playerRepository: PlayerRepository by inject()

        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is TeamDetailScreen -> {
                    TeamDetailPresenter(
                        teamId = screen.teamId,
                        teamRepository = teamRepository,
                        playerRepository = playerRepository,
                    )
                }

                else -> null
            }
        }
    }
}

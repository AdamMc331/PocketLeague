package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.bracket.domain.models.BracketType
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Player
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.adammcneilly.pocketleague.phase.domain.models.Phase
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.standings.domain.models.Standings
import com.adammcneilly.pocketleague.standings.domain.models.StandingsPlacement
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A state management container for the [EventOverviewScreen].
 */
@HiltViewModel
class EventOverviewViewModel @Inject constructor(
    private val fetchEventOverviewUseCase: FetchEventOverviewUseCase,
    private val dateTimeHelper: DateTimeHelper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _viewState: MutableStateFlow<EventOverviewViewState> =
        MutableStateFlow(EventOverviewViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        val initialEventId = savedStateHandle.get<String>("eventId")

        if (initialEventId != null) {
            eventSelected(initialEventId)
        }
    }

    /**
     * Indicates that the user selected an event with the given [eventId] and we should fetch the
     * overview information.
     */
    fun eventSelected(eventId: String) {
        _viewState.value = EventOverviewViewState.Loading

        viewModelScope.launch {
            val response = fetchEventOverviewUseCase(eventId)

            _viewState.value = when (response) {
                is Result.Success -> {
                    EventOverviewViewState.Success(
                        event = response.data.toDisplayModel(
                            dateTimeHelper = dateTimeHelper,
                            onPhaseClicked = { phase ->
                                val currentState =
                                    _viewState.value as? EventOverviewViewState.Success

                                if (currentState != null) {
                                    _viewState.value = currentState.copy(
                                        selectedPhase = phase
                                    )
                                }
                            },
                        ),
                    )
                }
                is Result.Error -> {
                    EventOverviewViewState.Error(
                        errorMessage = UIText.StringText("Error fetching event overview."),
                    )
                }
            }
        }
    }

    /**
     * When we navigate to a phase, we should clear our selected phase so that we don't
     * continue to show it.
     */
    fun navigatedToPhase() {
        val currentState =
            _viewState.value as? EventOverviewViewState.Success

        if (currentState != null) {
            _viewState.value = currentState.copy(
                selectedPhase = null,
            )
        }
    }
}

private fun StandingsPlacement.toDisplayModel(): StandingsPlacementDisplayModel {
    return StandingsPlacementDisplayModel(
        placement = this.placement.toString(),
        teamName = this.team.name,
        roster = this.team.roster.map(Player::gamerTag).joinToString(separator = " / "),
        teamLogo = this.team.lightThemeLogoImageUrl?.let(UIImage::Remote),
    )
}

private fun Standings.toDisplayModel(): StandingsDisplayModel {
    return StandingsDisplayModel(
        placements = this.placements.map(StandingsPlacement::toDisplayModel)
    )
}

private fun EventOverview.toDisplayModel(
    dateTimeHelper: DateTimeHelper,
    onPhaseClicked: (Phase) -> Unit,
): EventOverviewDisplayModel {
    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map { phase ->
            phase.toDisplayModel(
                onClick = {
                    onPhaseClicked(phase)
                },
            )
        },
        startDate = dateTimeHelper.getEventDayString(this.startDate),
        standings = this.standings.toDisplayModel(),
    )
}

private fun Phase.toDisplayModel(
    onClick: () -> Unit,
): PhaseDisplayModel {
    return PhaseDisplayModel(
        phaseName = this.name,
        numPools = this.numPools.toString(),
        bracketType = when (this.bracketType) {
            BracketType.CUSTOM -> "Custom"
            BracketType.SINGLE_ELIMINATION -> "SE"
            BracketType.DOUBLE_ELIMINATION -> "DE"
            BracketType.UNKNOWN -> "Unknown"
        },
        numEntrants = this.numEntrants.toString(),
        onClick = onClick,
    )
}

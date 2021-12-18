package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.bracket.domain.models.BracketType
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Player
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.adammcneilly.pocketleague.phase.domain.models.Phase
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.standings.domain.models.Standings
import com.adammcneilly.pocketleague.standings.domain.models.StandingsPlacement
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel
import com.ramcosta.composedestinations.EventOverviewScreenDestination
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

    private val navArgs = EventOverviewScreenDestination.argsFrom(savedStateHandle)

    init {
        viewModelScope.launch {
            val response = fetchEventOverviewUseCase(navArgs.eventId)

            _viewState.value = when (response) {
                is Result.Success -> {
                    EventOverviewViewState.Success(
                        event = response.data.toDisplayModel(dateTimeHelper),
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
}

private fun Player.toDisplayModel(): PlayerDisplayModel {
    return PlayerDisplayModel(
        flagImage = UIImage.Remote(""),
        gamerTag = this.gamerTag,
        realName = "",
        notes = null,
    )
}

private fun Team.toOverviewDisplayModel(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        lightLogoImage = UIImage.Remote(this.lightThemeLogoImageUrl),
        darkLogoImage = UIImage.Remote(this.darkThemeLogoImageUrl),
        roster = this.roster.map(Player::toDisplayModel)
    )
}

private fun StandingsPlacement.toDisplayModel(): StandingsPlacementDisplayModel {
    return StandingsPlacementDisplayModel(
        placement = this.placement.toString(),
        teamName = this.team.name,
        roster = this.team.roster.map(Player::gamerTag).joinToString(separator = " / "),
    )
}

private fun Standings.toDisplayModel(): StandingsDisplayModel {
    return StandingsDisplayModel(
        placements = this.placements.map(StandingsPlacement::toDisplayModel)
    )
}

private fun EventOverview.toDisplayModel(
    dateTimeHelper: DateTimeHelper,
): EventOverviewDisplayModel {
    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map { phase ->
            phase.toDisplayModel()
        },
        startDate = dateTimeHelper.getEventDayString(this.startDate),
        standings = this.standings.toDisplayModel(),
    )
}

private fun Phase.toDisplayModel(): PhaseDisplayModel {
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
    )
}

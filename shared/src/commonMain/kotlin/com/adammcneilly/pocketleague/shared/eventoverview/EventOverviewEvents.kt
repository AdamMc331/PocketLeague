package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.shared.Events
import com.adammcneilly.pocketleague.shared.datalayer.sources.graphql.requests.fetchEventOverview
import kotlinx.coroutines.flow.collectLatest

/**
 * Given an [eventId], request the overview information and update our screen as necessary.
 */
fun Events.getEventOverview(eventId: String) = screenCoroutine {
    dataRepository.smashGGApi.fetchEventOverview(eventId).collectLatest { dataResponse ->
        stateManager.updateScreen(EventOverviewState::class) { currentState ->
            when (dataResponse) {
                is Result.Success -> {
                    currentState.copy(
                        showLoading = false,
                        event = dataResponse.data.toDisplayModel(),
                    )
                }
                is Result.Error -> {
                    currentState.copy(
                        showLoading = false,
                        errorMessage = dataResponse.error.message,
                    )
                }
            }
        }
    }
}

private fun EventOverview.toDisplayModel(): EventOverviewDisplayModel {
    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map(PhaseOverview::toDisplayModel),
        startDate = "TODO: Event Overview Start Date",
        standings = this.standings.toDisplayModel(),
    )
}

private fun PhaseOverview.toDisplayModel(): PhaseDisplayModel {
    return PhaseDisplayModel(
        phaseId = this.id,
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

private fun Standings.toDisplayModel(): StandingsDisplayModel {
    return StandingsDisplayModel(
        placements = this.placements.map(StandingsPlacement::toDisplayModel)
    )
}

private fun StandingsPlacement.toDisplayModel(): StandingsPlacementDisplayModel {
    return StandingsPlacementDisplayModel(
        placement = this.placement.toString(),
        teamName = this.team.name,
        roster = this.team.roster.map(Player::gamerTag).joinToString(separator = " / "),
        teamLogo = this.team.lightThemeLogoImageUrl?.let(UIImage::Remote),
    )
}

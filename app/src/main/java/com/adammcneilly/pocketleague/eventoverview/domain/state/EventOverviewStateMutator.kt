@file:Suppress("TooManyFunctions")

package com.adammcneilly.pocketleague.eventoverview.domain.state

import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.event.api.GetEventOverviewUseCase
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewDisplayModel
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewViewState
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * A state management controller for the event overview screen that consumes [EventOverviewAction]s
 * and maps them into [EventOverviewViewState] entities.
 */
fun eventOverviewStateMutator(
    scope: CoroutineScope,
    getEventOverviewUseCase: GetEventOverviewUseCase,
    dateTimeHelper: DateTimeHelper,
) = stateFlowMutator<EventOverviewAction, EventOverviewViewState>(
    scope = scope,
    initialState = EventOverviewViewState(),
    actionTransform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is EventOverviewAction.FetchEventOverview ->
                    action.flow
                        .fetchEventOverviewMutations(
                            getEventOverviewUseCase,
                            dateTimeHelper,
                        )
                is EventOverviewAction.SelectPhase -> action.flow.selectPhaseMutations()
                is EventOverviewAction.NavigatedToPhaseDetail -> action.flow.clearPhaseMutations()
            }
        }
    }
)

private fun Flow<EventOverviewAction.SelectPhase>.selectPhaseMutations():
    Flow<Mutation<EventOverviewViewState>> {

    return this.map { action ->
        Mutation {
            copy(
                selectedPhaseId = action.phaseId,
            )
        }
    }
}

private fun Flow<EventOverviewAction.NavigatedToPhaseDetail>.clearPhaseMutations():
    Flow<Mutation<EventOverviewViewState>> {

    return this.map { action ->
        Mutation {
            copy(
                selectedPhaseId = null,
            )
        }
    }
}

private fun Flow<EventOverviewAction.FetchEventOverview>.fetchEventOverviewMutations(
    getEventOverviewUseCase: GetEventOverviewUseCase,
    dateTimeHelper: DateTimeHelper,
): Flow<Mutation<EventOverviewViewState>> {
    return this.flatMapLatest { action ->
        getEventOverviewUseCase.invoke(action.eventId)
            .map { result ->
                when (result) {
                    is GetEventOverviewUseCase.Result.Success -> {
                        successMutation(
                            event = result.eventOverview,
                            dateTimeHelper = dateTimeHelper,
                        )
                    }
                    is GetEventOverviewUseCase.Result.Error -> {
                        errorMutation()
                    }
                }
            }
            .onStart {
                emit(loadingMutation())
            }
    }
}

private fun loadingMutation() = Mutation<EventOverviewViewState> {
    copy(
        showLoading = true,
    )
}

private fun errorMutation() = Mutation<EventOverviewViewState> {
    copy(
        showLoading = false,
        errorMessage = UIText.StringText(
            "Fetching event overview failed.",
        ),
    )
}

private fun successMutation(
    event: EventOverview,
    dateTimeHelper: DateTimeHelper,
) = Mutation<EventOverviewViewState> {
    copy(
        showLoading = false,
        event = event.toDisplayModel(dateTimeHelper),
    )
}

private fun EventOverview.toDisplayModel(
    dateTimeHelper: DateTimeHelper,
): EventOverviewDisplayModel {
    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map { phase ->
            phase.toDisplayModel(
                onClick = {
                    // Coming soon
                },
            )
        },
        startDate = "TODO: Event Overview Start Date",
        standings = this.standings.toDisplayModel(),
    )
}

private fun PhaseOverview.toDisplayModel(
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

private fun Standings.toDisplayModel(): StandingsDisplayModel {
    return StandingsDisplayModel(
        placements = this.placements.map(com.adammcneilly.pocketleague.core.models.StandingsPlacement::toDisplayModel)
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

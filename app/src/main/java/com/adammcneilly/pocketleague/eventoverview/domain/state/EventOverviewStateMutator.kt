@file:Suppress("TooManyFunctions")

package com.adammcneilly.pocketleague.eventoverview.domain.state

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewDisplayModel
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewViewState
import com.adammcneilly.pocketleague.models.BracketType
import com.adammcneilly.pocketleague.models.EventOverview
import com.adammcneilly.pocketleague.models.PhaseOverview
import com.adammcneilly.pocketleague.models.Player
import com.adammcneilly.pocketleague.models.Standings
import com.adammcneilly.pocketleague.models.StandingsPlacement
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneOffset

/**
 * A state management controller for the event overview screen that consumes [EventOverviewAction]s
 * and maps them into [EventOverviewViewState] entities.
 */
fun eventOverviewStateMutator(
    scope: CoroutineScope,
    fetchEventOverviewUseCase: FetchEventOverviewUseCase,
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
                            fetchEventOverviewUseCase,
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
    fetchEventOverviewUseCase: FetchEventOverviewUseCase,
    dateTimeHelper: DateTimeHelper,
): Flow<Mutation<EventOverviewViewState>> {
    return this.flatMapLatest { action ->
        flow {
            emitLoading()

            val result = fetchEventOverviewUseCase.invoke(action.eventId)

            when (result) {
                is Result.Success -> {
                    emitSuccess(
                        event = result.data,
                        dateTimeHelper = dateTimeHelper,
                    )
                }
                is Result.Error -> {
                    emitError()
                }
            }
        }
    }
}

private suspend fun FlowCollector<Mutation<EventOverviewViewState>>.emitLoading() = this.emit(
    Mutation {
        copy(
            showLoading = true,
        )
    }
)

private suspend fun FlowCollector<Mutation<EventOverviewViewState>>.emitError() = this.emit(
    Mutation {
        copy(
            showLoading = false,
            errorMessage = UIText.StringText(
                "Fetching event overview failed.",
            ),
        )
    }
)

private suspend fun FlowCollector<Mutation<EventOverviewViewState>>.emitSuccess(
    event: EventOverview,
    dateTimeHelper: DateTimeHelper,
) = this.emit(
    Mutation {
        copy(
            showLoading = false,
            event = event.toDisplayModel(dateTimeHelper),
        )
    }
)

private fun EventOverview.toDisplayModel(
    dateTimeHelper: DateTimeHelper,
): EventOverviewDisplayModel {
    val startZonedDate = Instant.ofEpochSecond(this.startDateEpochSeconds).atZone(ZoneOffset.UTC)

    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map { phase ->
            phase.toDisplayModel(
                onClick = {
                    // Coming soon
                },
            )
        },
        startDate = dateTimeHelper.getEventDayString(startZonedDate),
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

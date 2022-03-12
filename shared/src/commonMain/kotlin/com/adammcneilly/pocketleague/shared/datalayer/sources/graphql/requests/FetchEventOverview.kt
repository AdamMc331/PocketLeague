package com.adammcneilly.pocketleague.shared.datalayer.sources.graphql.requests

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.datalayer.sources.graphql.ApolloBracketType
import com.adammcneilly.pocketleague.shared.datalayer.sources.graphql.SmashGGApolloClient
import com.adammcneilly.pocketleague.shared.graphql.EventOverviewQuery
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventPlayerFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.StandingsPlacementFragment
import com.adammcneilly.pocketleague.shared.graphql.type.StandingPaginationQuery
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Request the [EventOverview] information for the given [eventId].
 */
fun SmashGGApolloClient.fetchEventOverview(
    eventId: String,
): Flow<Result<EventOverview>> {
    val query = EventOverviewQuery(
        eventId = Optional.Present(eventId),
        standingsQuery = StandingPaginationQuery(),
    )

    return observeQuery(
        query = query,
        transform = { responseData ->
            responseData
                .event
                ?.eventOverviewFragment
                ?.toEventOverview()!!
        }
    )
}

private fun EventOverviewFragment.toEventOverview(): EventOverview {
    val summaryFragment = this.eventSummaryFragment

    return EventOverview(
        name = summaryFragment.name.orEmpty(),
        startDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        phases = this.phaseGroups
            ?.mapNotNull { phaseGroup ->
                phaseGroup?.phaseGroupFragment?.toPhase()
            }
            .orEmpty(),
        standings = Standings(
            placements = this.standings
                ?.nodes
                ?.mapNotNull { node ->
                    node
                        ?.standingsPlacementFragment
                        .let(StandingsPlacementFragment?::toStandingsPlacement)
                }
                .orEmpty()
        ),
        timeZone = TimeZone.UTC,
    )
}

private fun PhaseGroupFragment?.toPhase(): PhaseOverview {
    val overview = this?.phase?.phaseOverviewFragment

    return PhaseOverview(
        id = overview?.id.orEmpty(),
        groupId = this?.id.orEmpty(),
        numPools = overview?.groupCount ?: 0,
        numEntrants = overview?.numSeeds ?: 0,
        name = overview?.name.orEmpty(),
        bracketType = this?.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
        phaseOrder = overview?.phaseOrder ?: 0,
    )
}

private fun ApolloBracketType.toBracketType(): BracketType {
    return when (this) {
        ApolloBracketType.CUSTOM_SCHEDULE -> BracketType.CUSTOM
        ApolloBracketType.SINGLE_ELIMINATION -> BracketType.SINGLE_ELIMINATION
        ApolloBracketType.DOUBLE_ELIMINATION -> BracketType.DOUBLE_ELIMINATION
        else -> BracketType.UNKNOWN
    }
}

private fun StandingsPlacementFragment?.toStandingsPlacement(): StandingsPlacement {
    return StandingsPlacement(
        placement = this?.placement ?: 0,
        team = this?.entrant?.eventEntrantFragment.let(EventEntrantFragment?::toTeam),
    )
}

private fun EventEntrantFragment?.toTeam(): Team {
    return Team(
        name = this?.name.orEmpty(),
        lightThemeLogoImageUrl = this?.team?.images?.firstOrNull()?.url,
        darkThemeLogoImageUrl = this?.team?.images?.firstOrNull()?.url,
        roster = this?.team?.members?.mapNotNull { member ->
            member?.player?.eventPlayerFragment.let(EventPlayerFragment?::toPlayer)
        }.orEmpty(),
    )
}

private fun EventPlayerFragment?.toPlayer(): Player {
    return Player(
        countryCode = "",
        gamerTag = this?.gamerTag.orEmpty(),
        realName = "",
    )
}

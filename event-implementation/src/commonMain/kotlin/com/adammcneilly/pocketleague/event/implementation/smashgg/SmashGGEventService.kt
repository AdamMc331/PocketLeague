package com.adammcneilly.pocketleague.event.implementation.smashgg

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.implementation.graphql.EventOverviewQuery
import com.adammcneilly.pocketleague.event.implementation.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.StandingsPlacementFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.type.StandingPaginationQuery
import com.adammcneilly.pocketleague.event.implementation.smashgg.mappers.toBracketType
import com.adammcneilly.pocketleague.event.implementation.smashgg.mappers.toTeam
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A concrete implementation of [EventRepository] that will request information
 * from the smash.gg API.
 */
class SmashGGEventService : EventRepository {

    override fun fetchEventSummaries(): Flow<DataResult<List<EventSummary>>> {
        val query = EventSummaryListQuery(
            leagueSlug = Optional.Present("rlcs-2021-22-1"),
        )

        val response = smashGGApolloClient.query(query).toFlow()

        return response.map { dataResponse ->
            val events = dataResponse
                .data
                ?.league
                ?.events
                ?.nodes
                ?.mapNotNull {
                    it?.eventSummaryFragment?.toEvent()
                }
                .orEmpty()

            DataResult.Success(events)
        }
    }

    override fun fetchEventOverview(eventId: String): Flow<DataResult<EventOverview>> {
        val query = EventOverviewQuery(
            eventId = Optional.Present(eventId),
            standingsQuery = StandingPaginationQuery(),
        )

        val response = smashGGApolloClient.query(query).toFlow()

        return response.mapNotNull { dataResponse ->
            val overview = dataResponse
                .data
                ?.event
                ?.eventOverviewFragment
                ?.toEventOverview()

            if (overview != null) {
                DataResult.Success(overview)
            } else {
                DataResult.Error(Throwable("Unable to request EventOverview for id: $eventId"))
            }
        }
    }
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
        bracketType = this?.bracketType?.toBracketType()
            ?: BracketType.UNKNOWN,
        phaseOrder = overview?.phaseOrder ?: 0,
    )
}

private fun EventSummaryFragment.toEvent(): EventSummary {
    val startSeconds = (this.startAt as Int).toLong()
    val eventTimeZone = TimeZone.UTC
    val startDate = Instant.fromEpochSeconds(startSeconds).toLocalDateTime(eventTimeZone)

    return EventSummary(
        id = this.id.orEmpty(),
        eventName = this.name.orEmpty(),
        tournamentName = this.tournament?.name.orEmpty(),
        startDate = startDate,
        timeZone = eventTimeZone,
        numEntrants = this.numEntrants,
        isOnline = this.isOnline == true,
        tournamentImageUrl = this.tournament?.images?.firstOrNull()?.url.orEmpty(),
    )
}

private fun StandingsPlacementFragment?.toStandingsPlacement(): StandingsPlacement {
    return StandingsPlacement(
        placement = this?.placement ?: 0,
        team = this?.entrant?.eventEntrantFragment.let(EventEntrantFragment?::toTeam),
    )
}

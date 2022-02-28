package com.adammcneilly.pocketleague.event.implementation

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.event.api.EventListRequestBody
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.graphql.EventOverviewQuery
import com.adammcneilly.pocketleague.event.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.event.graphql.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.event.graphql.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.event.graphql.fragment.EventPlayerFragment
import com.adammcneilly.pocketleague.event.graphql.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.event.graphql.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.event.graphql.fragment.StandingsPlacementFragment
import com.adammcneilly.pocketleague.event.graphql.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.event.graphql.type.LeagueEventsQuery
import com.adammcneilly.pocketleague.event.graphql.type.StandingPaginationQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

typealias ApolloBracketType = com.adammcneilly.pocketleague.event.graphql.type.BracketType

/**
 * A concrete implementation of [EventRepository] that will request information
 * from the smash.gg API.
 */
class SmashGGEventService : EventRepository {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .addHttpInterceptor(SmashGGAuthorizationInterceptor())
        .build()

    override fun fetchEventSummaries(
        leagueSlug: String,
        requestBody: EventListRequestBody,
    ): Flow<Result<List<EventSummary>>> {
        val upcomingFilter = Optional.Present(
            LeagueEventsFilter(
                upcoming = Optional.presentIfNotNull(requestBody.upcoming),
            )
        )

        val eventsQuery = Optional.Present(
            LeagueEventsQuery(
                filter = upcomingFilter,
                perPage = Optional.presentIfNotNull(requestBody.numEvents),
            )
        )

        val query = EventSummaryListQuery(
            leagueSlug = Optional.Present(leagueSlug),
            eventsQuery = eventsQuery,
        )

        val response = apolloClient.query(query).toFlow()

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

            Result.Success(events)
        }
    }

    override fun fetchEventOverview(eventId: String): Flow<Result<EventOverview>> {
        val query = EventOverviewQuery(
            eventId = Optional.Present(eventId),
            standingsQuery = StandingPaginationQuery(),
        )

        val response = apolloClient.query(query).toFlow()

        return response.mapNotNull { dataResponse ->
            val overview = dataResponse
                .data
                ?.event
                ?.eventOverviewFragment
                ?.toEventOverview()

            if (overview != null) {
                Result.Success(overview)
            } else {
                Result.Error(Throwable("Unable to request EventOverview for id: $eventId"))
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
        bracketType = this?.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
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

package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.eventsummary.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.eventsummary.graphql.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.eventsummary.graphql.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.eventsummary.graphql.type.LeagueEventsQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

/**
 * A concrete implementation of [EventSummaryService] that will request information
 * from the smash.gg API.
 */
class SmashGGEventSummaryService : EventSummaryService {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .addHttpInterceptor(SmashGGAuthorizationInterceptor())
        .build()

    override suspend fun fetchUpcomingEventSummaries(leagueSlug: String): PLResult<List<EventSummary>> {
        val upcomingFilter = Optional.Present(
            LeagueEventsFilter(
                upcoming = Optional.Present(true),
            )
        )

        val eventsQuery = Optional.Present(
            LeagueEventsQuery(
                filter = upcomingFilter,
            )
        )

        val query = EventSummaryListQuery(
            leagueSlug = Optional.Present(leagueSlug),
            eventsQuery = eventsQuery,
        )

        val response = apolloClient.query(query).execute()

        val events = response
            .data
            ?.league
            ?.events
            ?.nodes
            ?.mapNotNull {
                it?.eventSummaryFragment?.toEvent()
            }
            .orEmpty()

        return PLResult.Success(events)
    }
}

private fun EventSummaryFragment.toEvent(): EventSummary {
    val startSeconds = (this.startAt as Int).toLong()

    return EventSummary(
        id = this.id.orEmpty(),
        eventName = this.name.orEmpty(),
        tournamentName = this.tournament?.name.orEmpty(),
        startDateEpochSeconds = startSeconds,
        numEntrants = this.numEntrants,
        isOnline = this.isOnline == true,
        tournamentImageUrl = this.tournament?.images?.firstOrNull()?.url.orEmpty(),
    )
}

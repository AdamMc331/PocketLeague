package com.adammcneilly.pocketleague.event.implementation

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.event.graphql.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.event.graphql.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.event.graphql.type.LeagueEventsQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A concrete implementation of [EventRepository] that will request information
 * from the smash.gg API.
 */
class SmashGGEventService : EventRepository {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .addHttpInterceptor(SmashGGAuthorizationInterceptor())
        .build()

    override fun fetchUpcomingEventSummaries(leagueSlug: String): Flow<Result<List<EventSummary>>> {
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

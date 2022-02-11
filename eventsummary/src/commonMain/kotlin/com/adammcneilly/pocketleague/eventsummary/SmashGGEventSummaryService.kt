package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PLResult
import com.apollographql.apollo3.ApolloClient

/**
 * A concrete implementation of [EventSummaryService] that will request information
 * from the smash.gg API.
 */
class SmashGGEventSummaryService : EventSummaryService {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://api.smash.gg/gql/alpha")
        .build()

    override suspend fun fetchUpcomingEventSummaries(leagueSlug: String): PLResult<List<EventSummary>> {
        TODO("Not yet implemented")
    }
}

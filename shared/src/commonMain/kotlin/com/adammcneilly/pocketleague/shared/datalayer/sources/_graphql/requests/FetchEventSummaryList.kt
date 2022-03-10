package com.adammcneilly.pocketleague.shared.datalayer.sources._graphql.requests

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.shared.datalayer.objects.EventListRequestBody
import com.adammcneilly.pocketleague.shared.datalayer.sources._graphql.SmashGGApolloClient
import com.adammcneilly.pocketleague.shared.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.shared.graphql.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.shared.graphql.type.LeagueEventsQuery
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun SmashGGApolloClient.fetchEventSummaries(
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

    return observeQuery(
        query = query,
        transform = { responseData ->
            responseData
                .league
                ?.events
                ?.nodes
                ?.mapNotNull {
                    it?.eventSummaryFragment?.toEvent()
                }
                .orEmpty()
        }
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

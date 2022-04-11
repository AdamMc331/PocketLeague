package com.adammcneilly.pocketleague.event.implementation.smashgg

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.implementation.graphql.EventOverviewQuery
import com.adammcneilly.pocketleague.event.implementation.graphql.EventSummaryListQuery
import com.adammcneilly.pocketleague.event.implementation.graphql.type.StandingPaginationQuery
import com.adammcneilly.pocketleague.event.implementation.smashgg.mappers.toEvent
import com.adammcneilly.pocketleague.event.implementation.smashgg.mappers.toEventOverview
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

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

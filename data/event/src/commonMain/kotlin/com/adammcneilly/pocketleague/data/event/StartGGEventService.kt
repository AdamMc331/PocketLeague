package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.RLCSTournamentFilter
import com.adammcneilly.pocketleague.data.startgg.StartGGApolloClient
import com.adammcneilly.pocketleague.data.startgg.TournamentListQuery
import com.adammcneilly.pocketleague.data.startgg.fragment.TournamentFragment
import com.adammcneilly.pocketleague.data.startgg.mappers.toEvent
import com.adammcneilly.pocketleague.data.startgg.type.TournamentQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

/**
 * A concrete implementation of a [RemoteEventService] that requests data from the supplied [apiClient].
 */
class StartGGEventService(
    private val apiClient: ApolloClient,
) : RemoteEventService {

    constructor() : this(StartGGApolloClient)

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        val upcomingEventsQuery = TournamentQuery(
            filter = Optional.present(
                RLCSTournamentFilter.copy(
                    upcoming = Optional.present(true),
                ),
            ),
        )

        val response = apiClient.query(TournamentListQuery(upcomingEventsQuery)).execute()

        return if (response.data != null && !response.hasErrors()) {
            val fragments = response.data?.tournaments?.nodes?.mapNotNull { it?.tournamentFragment }.orEmpty()

            val eventList = fragments.map(TournamentFragment::toEvent)

            Result.success(eventList)
        } else {
            Result.failure(Throwable("Unable to request upcoming events."))
        }
    }

    override suspend fun getEvent(eventId: String): Result<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventParticipants(eventId: String): Result<List<Team>> {
        TODO("Not yet implemented")
    }

    /**
     * Unable to figure out how to filter for ongoing events on the Start.gg side, may need to reach out to developer support on Discord.
     */
    override suspend fun getOngoingEvents(): Result<List<Event>> {
        val ongoingEventsQuery = TournamentQuery(
            filter = Optional.present(
                RLCSTournamentFilter.copy(
                    past = Optional.present(false),
                    upcoming = Optional.present(false),
                ),
            ),
        )

        val response = apiClient.query(TournamentListQuery(ongoingEventsQuery)).execute()

        return if (response.data != null && !response.hasErrors()) {
            val fragments = response.data?.tournaments?.nodes?.mapNotNull { it?.tournamentFragment }.orEmpty()

            val eventList = fragments.map(TournamentFragment::toEvent)

            Result.success(eventList)
        } else {
            Result.failure(Throwable("Unable to request ongoing events."))
        }
    }
}

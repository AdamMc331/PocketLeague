package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.RLCSTournamentFilter
import com.adammcneilly.pocketleague.data.startgg.StartGGApolloClient
import com.adammcneilly.pocketleague.data.startgg.TournamentListQuery
import com.adammcneilly.pocketleague.data.startgg.fragment.TournamentFragment
import com.adammcneilly.pocketleague.data.startgg.mappers.toEvent
import com.adammcneilly.pocketleague.data.startgg.mappers.toTeam
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

    override suspend fun getEventById(eventId: String): Result<Event> {
        val eventQuery = TournamentDetailQuery(
            id = Optional.present(eventId),
        )

        val response = apiClient.query(eventQuery).execute()

        val event = response.data?.tournament?.tournamentFragment?.toEvent()

        return if (event != null) {
            Result.success(event)
        } else {
            Result.failure(Throwable("Unable to request event for id: $eventId."))
        }
    }

    override suspend fun getEventParticipants(eventId: String): Result<List<Team>> {
        val eventQuery = TournamentParticipantsQuery(
            id = Optional.present(eventId),
        )

        val response = apiClient.query(eventQuery).execute()

        val teamList = response.data?.tournament?.teams?.nodes?.mapNotNull {
            it?.teamFragment?.toTeam()
        }.orEmpty()

        return Result.success(teamList)
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

    override suspend fun getEventByName(eventName: String): Result<Event> {
        throw UnsupportedOperationException("Fetching an event by name not supported by the start.gg api.")
    }
}

package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.StartGGApolloClient
import com.adammcneilly.pocketleague.data.startgg.TournamentListQuery
import com.adammcneilly.pocketleague.data.startgg.mappers.toEvent
import com.adammcneilly.pocketleague.data.startgg.type.TournamentPageFilter
import com.adammcneilly.pocketleague.data.startgg.type.TournamentQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

class StartGGEventService(
    private val apiClient: ApolloClient,
) : RemoteEventService {

    constructor() : this(StartGGApolloClient)

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        val upcomingEventsQuery = TournamentQuery(
            filter = Optional.present(
                TournamentPageFilter(
                    upcoming = Optional.present(true),
                    name = Optional.present("RLCS"),
                    // We're hardcoding the Rocket League video game Id.
                    // TODO: Let's see if there's another way to search for RLCS events? Maybe by league or something?
                    videogameIds = Optional.present(listOf("14")),
                ),
            ),
        )

        val response = apiClient.query(TournamentListQuery(upcomingEventsQuery)).execute()

        return if (response.data != null && !response.hasErrors()) {
            val eventList = response.data?.tournaments?.nodes?.mapNotNull {
                it?.tournamentFragment?.toEvent()
            }.orEmpty()

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
                TournamentPageFilter(
                    past = Optional.present(false),
                    upcoming = Optional.present(false),
                    name = Optional.present("RLCS"),
                    // We're hardcoding the Rocket League video game Id.
                    // TODO: Let's see if there's another way to search for RLCS events? Maybe by league or something?
                    videogameIds = Optional.present(listOf("14")),
                ),
            ),
        )

        val response = apiClient.query(TournamentListQuery(ongoingEventsQuery)).execute()

        return if (response.data != null && !response.hasErrors()) {
            val fragments = response.data?.tournaments?.nodes?.map { it?.tournamentFragment }.orEmpty()

            val rlcsOwnerIds = fragments.filter {
                it?.name?.contains("RLCS") == true
            }.map {
                it?.owner?.id
            }

            val eventList = fragments.mapNotNull { it?.toEvent() }

            Result.success(eventList)
        } else {
            Result.failure(Throwable("Unable to request ongoing events."))
        }
    }
}

package com.adammcneilly.pocketleague.event.data.remote

import com.adammcneilly.pocketleague.EventListQuery
import com.adammcneilly.pocketleague.TournamentDetailQuery
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary
import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage
import com.adammcneilly.pocketleague.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.type.LeagueEventsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.BigDecimal
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

/**
 * A concrete [EventService] that makes its calls to the Smash.gg [api].
 */
class SmashGGEventService @Inject constructor(
    private val api: ApolloClient,
) : EventService {

    override suspend fun fetchSwissStage(eventName: String): Result<SwissStage> {
        val query = TournamentDetailQuery(slug = eventName.toInput())

        val result = api.query(query).await()

        val mainEvent = result.data!!.tournament!!.events!!.firstOrNull { event ->
            event!!.name == "Main Event"
        }

        val dayOneSwiss = mainEvent!!.phases!!.firstOrNull { phase ->
            phase!!.name == "Day 1: Swiss Matches"
        }

        val nodesByRound = dayOneSwiss!!.sets!!.nodes!!.groupBy { setNode ->
            setNode!!.fullRoundText
        }

        val swissRoundsList = nodesByRound.map { entry ->
            val name = entry.key
            val rounds = entry.value.mapNotNull { it!!.toSeriesOverview() }

            SwissRound(
                roundDescription = name.orEmpty(),
                series = rounds,
            )
        }

        return Result.Success(
            data = SwissStage(
                rounds = swissRoundsList,
            )
        )
    }

    override suspend fun fetchUpcomingEvents(leagueSlug: String): Result<List<EventSummary>> {
        val upcomingFilter = LeagueEventsFilter(
            upcoming = true.toInput(),
        ).toInput()

        val eventsQuery = LeagueEventsQuery(
            filter = upcomingFilter,
        ).toInput()

        val query = EventListQuery(
            leagueSlug = leagueSlug.toInput(),
            eventsQuery = eventsQuery,
        )

        val response = api.query(query).await()

        val events = response
            .data
            ?.league
            ?.events
            ?.nodes
            ?.mapNotNull {
                it?.toEvent()
            }
            .orEmpty()

        return Result.Success(events)
    }
}

private fun EventListQuery.Node.toEvent(): EventSummary {
    val startSeconds = (this.startAt as BigDecimal).toLong()
    val startDate = Instant.ofEpochSecond(startSeconds).atOffset(ZoneOffset.UTC)

    return EventSummary(
        id = this.id.orEmpty(),
        eventName = this.name.orEmpty(),
        tournamentName = this.tournament?.name.orEmpty(),
        startDate = startDate.toZonedDateTime(),
    )
}

private fun TournamentDetailQuery.Node.toSeriesOverview(): SeriesOverview? {
    val slotOne = this.slots!![0]
    val slotTwo = this.slots[1]

    val teamOne = slotOne!!.entrant!!.toTeam()
    val teamTwo = slotTwo!!.entrant!!.toTeam()

    val teamOneScore = slotOne.standing!!.stats!!.score!!.displayValue
    val teamTwoScore = slotTwo.standing!!.stats!!.score!!.displayValue

    return if (teamOneScore != null && teamTwoScore != null) {
        SeriesOverview(
            teamOne = teamOne,
            teamTwo = teamTwo,
            teamOneWins = teamOneScore.toInt(),
            teamTwoWins = teamTwoScore.toInt(),
        )
    } else {
        null
    }
}

private fun TournamentDetailQuery.Entrant.toTeam(): Team {
    return Team(
        name = this.name.orEmpty(),
        lightThemeLogoImageUrl = "",
        darkThemeLogoImageUrl = "",
        roster = emptyList(),
    )
}

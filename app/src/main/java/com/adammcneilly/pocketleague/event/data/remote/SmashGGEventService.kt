package com.adammcneilly.pocketleague.event.data.remote

import com.adammcneilly.pocketleague.EventOverviewQuery
import com.adammcneilly.pocketleague.EventSummaryListQuery
import com.adammcneilly.pocketleague.TournamentDetailQuery
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.smashgg.SmashGGModelMapper
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.fragment.EventSummaryFragment
import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage
import com.adammcneilly.pocketleague.type.LeagueEventsFilter
import com.adammcneilly.pocketleague.type.LeagueEventsQuery
import com.adammcneilly.pocketleague.type.StandingPaginationQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.BigDecimal
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
import javax.inject.Inject

typealias ApolloBracketType = com.adammcneilly.pocketleague.type.BracketType

/**
 * A concrete [EventService] that makes its calls to the Smash.gg [api].
 */
class SmashGGEventService @Inject constructor(
    private val api: ApolloClient,
    private val modelMapper: SmashGGModelMapper,
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

        val query = EventSummaryListQuery(
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
                it?.fragments?.eventSummaryFragment?.toEvent()
            }
            .orEmpty()

        return Result.Success(events)
    }

    override suspend fun fetchEventOverview(eventId: String): Result<EventOverview> {
        val query = EventOverviewQuery(
            eventId = eventId.toInput(),
            standingsQuery = StandingPaginationQuery(),
        )

        val response = api.query(query).await()

        val overview = response
            .data
            ?.event
            ?.fragments
            ?.eventOverviewFragment
            .let(modelMapper::eventOverviewFragmentToEventOverview)

        return Result.Success(overview)
    }
}

private fun EventSummaryFragment.toEvent(): EventSummary {
    val startSeconds = (this.startAt as BigDecimal).toLong()

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

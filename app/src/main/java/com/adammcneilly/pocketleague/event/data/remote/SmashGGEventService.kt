package com.adammcneilly.pocketleague.event.data.remote

import com.adammcneilly.pocketleague.TournamentDetailQuery
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
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

        val swissRoundsList = nodesByRound!!.map { entry ->
            val name = entry.key
            val rounds = entry.value.mapNotNull { it!!.toSeriesOverview() }

            SwissRound(
                roundDescription = name.orEmpty(),
                series = rounds,
            )
        }

        return Result.Success(
            data = SwissStage(
                rounds = swissRoundsList.orEmpty(),
            )
        )
    }
}

private fun TournamentDetailQuery.Node.toSeriesOverview(): SeriesOverview? {
    val slotOne = this.slots!!.get(0)
    val slotTwo = this.slots!!.get(1)

    val teamOne = slotOne!!.entrant!!.toTeam()
    val teamTwo = slotTwo!!.entrant!!.toTeam()

    val teamOneScore = slotOne!!.standing!!.stats!!.score!!.displayValue
    val teamTwoScore = slotTwo!!.standing!!.stats!!.score!!.displayValue

    if (teamOne != null && teamTwo != null && teamOneScore != null && teamTwoScore != null) {
        return SeriesOverview(
            teamOne = teamOne,
            teamTwo = teamTwo,
            teamOneWins = teamOneScore.toInt(),
            teamTwoWins = teamTwoScore.toInt(),
        )
    } else {
        return null
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

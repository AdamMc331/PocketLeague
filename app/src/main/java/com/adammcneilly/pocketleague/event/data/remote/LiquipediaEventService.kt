package com.adammcneilly.pocketleague.event.data.remote

import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class LiquipediaEventService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
) : EventService {

    override suspend fun fetchSwissStage(eventName: String): SwissStage {
        val liquipediaResponse = api.fetchPage(
            page = eventName,
        )

        val body = liquipediaResponse.body()?.parse?.text?.x

        val rounds = mutableListOf<SwissRound>()

        if (body != null) {
            val doc = Jsoup.parse(body)

            val brackets = doc.select("div.brkts-matchlist")

            val parsedRounds = brackets.map { bracket ->
                parseRound(bracket)
            }

            rounds.addAll(parsedRounds)
        }

        return SwissStage(
            rounds = rounds,
        )
    }

    private fun parseRound(bracket: Element): SwissRound {
        val title = bracket.selectFirst("div.brkts-matchlist-title")?.text()

        val matches = bracket.select("div.brkts-matchlist-match")

        val seriesOverviews = matches.map { match ->
            parseSeriesOverview(match)
        }

        return SwissRound(
            roundDescription = title.orEmpty(),
            series = seriesOverviews,
        )
    }

    private fun parseSeriesOverview(match: Element): SeriesOverview {
        val teams = match.select("div.brkts-matchlist-opponent").map { it.text() }
        val scores = match.select("div.brkts-matchlist-score").map { it.text() }

        val teamOne = Team(
            name = teams[0],
            lightThemeLogoImageUrl = "",
            darkThemeLogoImageUrl = "",
            roster = emptyList(),
        )

        val teamTwo = Team(
            name = teams[1],
            lightThemeLogoImageUrl = "",
            darkThemeLogoImageUrl = "",
            roster = emptyList(),
        )

        return SeriesOverview(
            teamOne = teamOne,
            teamTwo = teamTwo,
            teamOneWins = scores[0].toInt(),
            teamTwoWins = scores[1].toInt()
        )
    }
}

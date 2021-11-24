package com.adammcneilly.pocketleague.event.data.remote

import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.html.HTMLElement
import com.adammcneilly.pocketleague.core.html.HTMLParser
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage
import javax.inject.Inject

class LiquipediaEventService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
    private val htmlParser: HTMLParser,
) : EventService {

    override suspend fun fetchSwissStage(eventName: String): SwissStage {
        val liquipediaResponse = api.fetchPage(
            page = eventName,
        )

        val body = liquipediaResponse.body()?.parse?.text?.x

        val rounds = mutableListOf<SwissRound>()

        if (body != null) {
            htmlParser.setHTML(body)

            val brackets = htmlParser.selectAll(
                elementType = "div",
                elementClass = "brkts-matchlist",
            )

            val parsedRounds = brackets.map { bracket ->
                parseRound(bracket)
            }

            rounds.addAll(parsedRounds)
        }

        return SwissStage(
            rounds = rounds,
        )
    }

    private fun parseRound(bracket: HTMLElement): SwissRound {
        val title = bracket.selectFirst(
            elementType = "div",
            elementClass = "brkts-matchlist-title",
        )?.getText()

        val matches = bracket.selectAll(
            elementType = "div",
            elementClass = "brkts-matchlist-match",
        )

        val seriesOverviews = matches.map { match ->
            parseSeriesOverview(match)
        }

        return SwissRound(
            roundDescription = title.orEmpty(),
            series = seriesOverviews,
        )
    }

    private fun parseSeriesOverview(match: HTMLElement): SeriesOverview {
        val teams = match.selectAll(
            elementType = "div",
            elementClass = "brkts-matchlist-opponent",
        ).map { it.getText() }

        val scores = match.selectAll(
            elementType = "div",
            elementClass = "brkts-matchlist-score",
        ).map { it.getText() }

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

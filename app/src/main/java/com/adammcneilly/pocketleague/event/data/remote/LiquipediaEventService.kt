package com.adammcneilly.pocketleague.event.data.remote

import android.util.Log
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.event.data.EventService
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class LiquipediaEventService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
) : EventService {

    override suspend fun fetchNARegional3() {
        val liquipediaResponse = api.fetchNARegional3()

        val body = liquipediaResponse.body()?.parse?.text?.x

        if (body != null) {
            val doc = Jsoup.parse(body)

            val brackets = doc.select("div.brkts-matchlist")

            brackets.forEach { bracket ->
                parseBracket(bracket)
            }
        }
    }

    private fun parseBracket(bracket: Element) {
        val title = bracket.selectFirst("div.brkts-matchlist-title")?.text()
        Log.d("LiquipediaService", "Title: $title")

        val matches = bracket.select("div.brkts-matchlist-match")

        matches.forEach { match ->
            parseMatch(match)
        }
    }

    private fun parseMatch(match: Element) {
        val teams = match.select("div.brkts-matchlist-opponent").map { it.text() }
        val scores = match.select("div.brkts-matchlist-score").map { it.text() }

        val (teamOne, teamTwo) = teams
        val (scoreOne, scoreTwo) = scores
        Log.d("LiquipediaService", "Match result: $teamOne $scoreOne | $scoreTwo $teamTwo")
    }
}

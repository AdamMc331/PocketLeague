package com.adammcneilly.pocketleague.teamlist.data.remote

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

class LiquipediaTeamListService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
) : TeamListService {

    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val liquipediaResponse = api.fetchTeamsPage()

        val body = liquipediaResponse.body()?.parse?.text?.x

        val teams = mutableListOf<Team>()

        if (body != null) {
            val doc = Jsoup.parse(body)

            val teamNodes = doc.select("div[class*=template-box]")

            val parsedTeams = teamNodes.mapNotNull { teamNode ->
                parseTeam(teamNode)
            }

            teams.addAll(parsedTeams)
        }

        return Result.Success(teams.toList())
    }

    private fun parseTeam(teamNode: Element): Team? {
        val teamName = parseTeamName(teamNode)
        val logoImage = parseLightModeImageUrl(teamNode)

        return if (teamName != null && logoImage != null) {
            Team(
                name = teamName,
                logoImageUrl = logoImage,
                roster = emptyList(),
            )
        } else {
            null
        }
    }

    private fun parseTeamName(teamNode: Element): String? {
        return try {
            teamNode
                .selectFirst("span[class*=team-template-text]")
                .text()
        } catch (e: Exception) {
            null
        }
    }

    private fun parseLightModeImageUrl(teamNode: Element): String? {
        return try {
            val baseUrl = "https://liquipedia.net/"

            baseUrl + teamNode
                .selectFirst("span.team-template-lightmode")
                .selectFirst("img")
                .attributes()
                .get("src")
        } catch (e: Exception) {
            null
        }
    }
}

package com.adammcneilly.pocketleague.teamlist.data.remote

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Player
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
        val lightImage = parseLightModeImageUrl(teamNode)
        val darkImage = parseDarkModeImageUrl(teamNode)
        val roster = parseRoster(teamNode)

        return if (teamName != null) {
            Team(
                name = teamName,
                lightThemeLogoImageUrl = lightImage.orEmpty(),
                darkThemeLogoImageUrl = darkImage.orEmpty(),
                roster = roster,
            )
        } else {
            null
        }
    }

    private fun parseTeamName(teamNode: Element): String? {
        return try {
            teamNode
                .selectFirst("span[class*=team-template-text]")
                ?.text()
        } catch (e: Exception) {
            null
        }
    }

    private fun parseLightModeImageUrl(teamNode: Element): String? {
        return try {
            val baseUrl = "https://liquipedia.net/"

            baseUrl + teamNode
                .selectFirst("span.team-template-lightmode")
                ?.selectFirst("img")
                ?.attributes()
                ?.get("src")
        } catch (e: Exception) {
            null
        }
    }

    private fun parseDarkModeImageUrl(teamNode: Element): String? {
        return try {
            val baseUrl = "https://liquipedia.net/"

            baseUrl + teamNode
                .selectFirst("span.team-template-darkmode")
                ?.selectFirst("img")
                ?.attributes()
                ?.get("src")
        } catch (e: Exception) {
            null
        }
    }

    private fun parseRoster(teamNode: Element): List<Player> {
        return teamNode.select("tr").mapNotNull { playerRow ->
            parsePlayer(playerRow)
        }
    }

    private fun parsePlayer(playerRow: Element): Player? {
        val cells = playerRow.select("td")

        val gamerTag = cells.getOrNull(0)?.text()
        val playerName = cells.getOrNull(1)?.text()
        val notes = cells.getOrNull(2)?.text()

        return if (gamerTag != null && playerName != null) {
            Player(
                countryCode = "us",
                gamerTag = gamerTag,
                realName = playerName,
                notes = notes,
            )
        } else {
            null
        }
    }
}

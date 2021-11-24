package com.adammcneilly.pocketleague.teamlist.data.remote

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Player
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.html.HTMLElement
import com.adammcneilly.pocketleague.core.html.HTMLParser
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import javax.inject.Inject

class LiquipediaTeamListService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
    private val htmlParser: HTMLParser,
) : TeamListService {

    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val liquipediaResponse = api.fetchPage(
            page = "Portal:Teams",
        )

        val body = liquipediaResponse.body()?.parse?.text?.x

        val teams = mutableListOf<Team>()

        if (body != null) {
            htmlParser.setHTML(body)

            val teamNodes = htmlParser.selectAll(
                elementType = "div",
                elementClass = "template-box",
            )

            val parsedTeams = teamNodes.mapNotNull { teamNode ->
                parseTeam(teamNode)
            }

            teams.addAll(parsedTeams)
        }

        return Result.Success(teams.toList())
    }

    private fun parseTeam(teamNode: HTMLElement): Team? {
        val teamName = parseTeamName(teamNode)
        val lightImage = parseLightModeImageUrl(teamNode)
        val darkImage = parseDarkModeImageUrl(teamNode)
        val roster = parseRoster(teamNode)

        return if (teamName != null) {
            Team(
                name = teamName,
                lightThemeLogoImageUrl = lightImage,
                darkThemeLogoImageUrl = darkImage,
                roster = roster,
            )
        } else {
            null
        }
    }

    private fun parseTeamName(teamNode: HTMLElement): String? {
        return teamNode
            .selectAll("span", "team-template-text")
            .firstOrNull()
            ?.getText()
    }

    private fun parseLightModeImageUrl(teamNode: HTMLElement): String {
        val imageUrl = teamNode
            .selectAll("span", "team-template-lightmode")
            .firstOrNull()
            ?.selectAll("img", "")
            ?.firstOrNull()
            ?.getAttribute("src")

        return "https://liquipedia.net/$imageUrl"
    }

    private fun parseDarkModeImageUrl(teamNode: HTMLElement): String {
        val imageUrl = teamNode
            .selectAll("span", "team-template-darkmode")
            .firstOrNull()
            ?.selectAll("img", "")
            ?.firstOrNull()
            ?.getAttribute("src")

        return "https://liquipedia.net/$imageUrl"
    }

    private fun parseRoster(teamNode: HTMLElement): List<Player> {
        return teamNode.selectAll("tr", "").mapNotNull { playerRow ->
            parsePlayer(playerRow)
        }
    }

    private fun parsePlayer(playerRow: HTMLElement): Player? {
        val cells = playerRow.selectAll("td", "")

        val gamerTag = cells.getOrNull(0)?.getText()
        val playerName = cells.getOrNull(1)?.getText()
        val notes = cells.getOrNull(2)?.getText()

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

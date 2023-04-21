package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
 * A custom implementation of [TeamRepository] that will send and request data
 * from the Liquipedia Rocket League website.
 */
class LiquipediaTeamRepository : TeamRepository {
    override fun getFavoriteTeams(): Flow<List<Team>> {
        throw UnsupportedOperationException("Fetching favorite teams is not supported by the Liquipedia API.")
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        return flow {
            val teamDoc = withContext(Dispatchers.IO) {
                Jsoup.connect("https://liquipedia.net/rocketleague/Portal:Teams").get()
            }

            val regions = listOf(
                "Europe",
                "North America",
                "Oceania",
                "South America",
                "Middle East and North Africa",
                "Asia-Pacific",
            )

            val allTeams = regions
                .map { regionName ->
                    teamDoc
                        .selectActiveTeams(regionName)
                        .map { element ->
                            element.parseTeam(regionName)
                        }
                }
                .flatten()

            emit(allTeams)
        }
    }

    override suspend fun insertTeams(teams: List<Team>) {
        throw UnsupportedOperationException("Inserting teams is not supported by the Liquipedia API.")
    }

    override suspend fun updateIsFavorite(teamId: String, isFavorite: Boolean) {
        throw UnsupportedOperationException("Favoriting teams is not supported by the Liquipedia API.")
    }
}

/**
 * In the Liquipedia web page for teams, we know a team is active if it exists within a `toggle-group` div (because there's toggles to show
 * roster information). Any disbanded teams are in a separate div, but they also share the `team-template-team-standard` class, so we need
 * to differentiate based on that.
 *
 * If we wanted to select teams for a specific region, we can look for toggle groups that are preceded by a header
 * with the region's name. Example: "h3:contains(North America)+.toggle-group .team-template-team-standard"
 */
private fun Element.selectActiveTeams(
    regionName: String,
) = this
    .select("h3:contains($regionName)+.toggle-group .team-template-team-standard")

private fun Element.selectTeamText() = this.select(".team-template-text").text()

private fun Element.selectTeamImageSource(lightTheme: Boolean): String {
    val themeKey = if (lightTheme) {
        "lightmode"
    } else {
        "darkmode"
    }

    return this
        .select(".team-template-image-icon.team-template-$themeKey")
        .select("a img")
        .attr("abs:src")
}

/**
 * Assuming this element represents an element returned by [selectActiveTeams], we can use this to parse a [Team] entity from it.
 */
private fun Element.parseTeam(
    regionName: String,
): Team {
    return Team(
        id = selectTeamText(),
        name = selectTeamText(),
        lightThemeImageURL = selectTeamImageSource(lightTheme = true),
        darkThemeImageURL = selectTeamImageSource(lightTheme = false),
        isActive = true,
        regionName = regionName,
    )
}

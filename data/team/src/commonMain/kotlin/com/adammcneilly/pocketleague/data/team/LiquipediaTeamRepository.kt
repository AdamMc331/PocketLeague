package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

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

            // Since all of this gets stored in a database
            // This flow only works if the emitted items have all of the properties that they need.
            // .team-template-text
            val teamList = teamDoc
                .select(".toggle-group .team-template-team-standard")
                .map { element ->
                    val name = element.select(".team-template-text").text()
                    val lightModeImage = element.select(".team-template-image-icon.team-template-lightmode")
                    val darkModeImage = element.select(".team-template-image-icon.team-template-darkmode")

                    val lightModeImageUrl = lightModeImage.select("a img").attr("abs:src")
                    val darkModeImageUrl = darkModeImage.select("a img").attr("abs:src")

                    Team(
                        id = name,
                        name = name,
                        isActive = true,
                        lightThemeImageURL = lightModeImageUrl,
                        darkThemeImageURL = darkModeImageUrl,
                    )
                }

            emit(teamList)
        }
    }

    override suspend fun insertTeams(teams: List<Team>) {
        throw UnsupportedOperationException("Inserting teams is not supported by the Liquipedia API.")
    }

    override suspend fun updateIsFavorite(teamId: String, isFavorite: Boolean) {
        throw UnsupportedOperationException("Favoriting teams is not supported by the Liquipedia API.")
    }
}

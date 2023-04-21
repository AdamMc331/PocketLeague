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
class LiquipediaTeamRepository() : TeamRepository {
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
            val teamList = teamDoc
                .select(".toggle-group .team-template-team-standard .team-template-text")
                .mapIndexed { index, element ->
                    Team(
                        id = "TEAM_$index",
                        name = element.text(),
                        isActive = true,
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

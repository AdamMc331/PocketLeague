package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.models.Event
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.models.MatchTeamResult
import com.adammcneilly.pocketleague.shared.models.Team
import com.adammcneilly.pocketleague.shared.screens.ScreenState

private val placeholderMatch = Match(
    id = "",
    event = Event(
        id = "",
        name = "",
        startDate = null,
        endDate = null,
        imageUrl = null,
    ),
    date = null,
    blueTeam = MatchTeamResult(
        score = -1,
        winner = false,
        team = Team(
            id = "",
            name = "",
            imageUrl = null,
        ),
    ),
    orangeTeam = MatchTeamResult(
        score = -1,
        winner = false,
        team = Team(
            id = "",
            name = "",
            imageUrl = null,
        ),
    ),
)

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val showLoading: Boolean = true,
    val upcomingEvents: List<Event>? = null,
    val recentMatches: List<Match>? = listOf(placeholderMatch, placeholderMatch, placeholderMatch),
    val errorMessage: String? = null,
) : ScreenState

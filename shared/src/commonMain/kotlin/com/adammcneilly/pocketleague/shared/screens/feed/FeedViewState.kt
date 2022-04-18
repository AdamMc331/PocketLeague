package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.models.Event
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val showLoading: Boolean = true,
    val upcomingEvents: List<Event>? = null,
    val recentMatches: List<Match>? = null,
    val errorMessage: String? = null,
) : ScreenState

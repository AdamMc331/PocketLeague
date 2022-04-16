package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.models.Event
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screen.Feed] screen.
 */
data class FeedState(
    val showLoading: Boolean = true,
    val upcomingEvents: List<Event>? = null,
) : ScreenState

package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.shared.ScreenState

/**
 * Defines the view state of our event overview screen.
 */
data class EventOverviewState(
    val showLoading: Boolean = true,
    val eventId: String,
) : ScreenState

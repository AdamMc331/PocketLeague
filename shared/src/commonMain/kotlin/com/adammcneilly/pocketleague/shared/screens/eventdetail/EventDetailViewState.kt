package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 *
 * @property[showLoading] True if we should show some loading indicator on the screen, false otherwise.
 * @property[eventId] The unique identifier of the event we are showing detailed information for.
 */
data class EventDetailViewState(
    val showLoading: Boolean = true,
    val eventId: String = "",
) : ScreenState

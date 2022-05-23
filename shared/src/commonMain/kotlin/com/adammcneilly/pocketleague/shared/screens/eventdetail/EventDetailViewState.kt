package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 *
 * @property[eventId] The unique identifier of the event we are showing detailed information for.
 * @property[showLoading] True if we should show some loading indicator on the screen, false otherwise.
 * @property[eventDetail] If available, user friendly information about the event.
 * @property[errorMessage] A user friendly description of what went wrong, if we had an issue displaying
 * this event.
 */
data class EventDetailViewState(
    val eventId: String = "",
    val showLoading: Boolean = true,
    val eventDetail: EventDetailDisplayModel? = null,
    val errorMessage: String? = null,
) : ScreenState

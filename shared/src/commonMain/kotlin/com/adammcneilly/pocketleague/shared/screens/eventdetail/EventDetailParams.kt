package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.shared.screens.ScreenParams

/**
 * Navigation parameters that will be passed into the event detail screen.
 */
data class EventDetailParams(
    val eventId: String,
) : ScreenParams

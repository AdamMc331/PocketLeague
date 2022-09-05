package com.adammcneilly.pocketleague.feature.eventdetail

import com.adammcneilly.pocketleague.feature.core.ScreenParams

/**
 * Navigation parameters that will be passed into the event detail screen.
 */
data class EventDetailParams(
    val eventId: String,
) : ScreenParams

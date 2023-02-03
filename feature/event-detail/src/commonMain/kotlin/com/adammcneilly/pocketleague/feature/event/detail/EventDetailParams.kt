package com.adammcneilly.pocketleague.feature.event.detail

import com.adammcneilly.pocketleague.core.feature.ScreenParams

/**
 * Navigation parameters that will be passed into the event detail screen.
 */
data class EventDetailParams(
    val eventId: String
) : ScreenParams

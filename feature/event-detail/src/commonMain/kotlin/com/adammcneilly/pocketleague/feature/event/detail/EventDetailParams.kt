package com.adammcneilly.pocketleague.feature.event.detail

import com.adammcneilly.pocketleague.core.feature.ScreenParams
import com.adammcneilly.pocketleague.core.models.Event

/**
 * Navigation parameters that will be passed into the event detail screen.
 */
data class EventDetailParams(
    val eventId: Event.Id,
) : ScreenParams

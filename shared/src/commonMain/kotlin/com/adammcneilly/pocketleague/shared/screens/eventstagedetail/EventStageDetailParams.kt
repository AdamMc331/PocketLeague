package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.feature.ScreenParams
import com.adammcneilly.pocketleague.core.models.Event

/**
 * Navigation parameters that will be passed into the event stage detail screen.
 */
data class EventStageDetailParams(
    val eventId: Event.Id,
    val stageId: String,
) : ScreenParams

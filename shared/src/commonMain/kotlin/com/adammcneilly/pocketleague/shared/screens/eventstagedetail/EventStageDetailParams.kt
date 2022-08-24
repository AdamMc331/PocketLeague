package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.feature.core.ScreenParams

/**
 * Navigation parameters that will be passed into the event stage detail screen.
 */
data class EventStageDetailParams(
    val eventId: String,
    val stageId: String,
) : ScreenParams

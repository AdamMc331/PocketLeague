package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.feature.ScreenParams

/**
 * Navigation parameters that will be passed into the event stage detail screen.
 */
data class EventStageDetailParams(
    val eventId: String,
    val stageId: String
) : ScreenParams

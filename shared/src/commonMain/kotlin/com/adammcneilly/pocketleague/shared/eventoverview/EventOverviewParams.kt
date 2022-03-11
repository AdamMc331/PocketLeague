package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.shared.ScreenParams
import kotlinx.serialization.Serializable

/**
 * The custom [ScreenParams] to be supplied to the Event Overview screen.
 */
@Serializable
data class EventOverviewParams(val eventId: String) : ScreenParams

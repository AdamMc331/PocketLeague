package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.shared.models.Event

/**
 * Converts an [OctaneGGEvent] entity into an [Event] within the Pocket League domain.
 */
fun OctaneGGEvent.toEvent(): Event {
    return Event(
        id = this.id ?: "ID Not Available",
        name = this.name ?: "Name Not Available",
    )
}

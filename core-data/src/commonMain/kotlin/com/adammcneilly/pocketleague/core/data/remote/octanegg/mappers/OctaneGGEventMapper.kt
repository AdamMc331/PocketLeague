package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGStage
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier

/**
 * Converts an [OctaneGGEvent] entity into an [Event] within the Pocket League domain.
 */
fun OctaneGGEvent.toEvent(): Event {
    return Event(
        id = this.id ?: "ID Not Available",
        name = this.name ?: "Name Not Available",
        startDateUTC = this.startDate,
        endDateUTC = this.endDate,
        imageUrl = this.image,
        stages = this.stages?.map(OctaneGGStage::toEventStage).orEmpty(),
        tier = this.tier.toEventTier(),
        region = this.region.toEventRegion(),
        mode = this.mode.toString(),
        lan = this.lan == true,
        prize = this.prize?.toPrize(),
    )
}

/**
 * Attempts to convert the supplied String to an event tier, with a fallback
 * if necessary.
 */
private fun String?.toEventTier(): EventTier {
    return when (this) {
        "S" -> EventTier.S
        "A" -> EventTier.A
        "B" -> EventTier.B
        "C" -> EventTier.C
        "D" -> EventTier.D
        else -> EventTier.Unknown
    }
}

/**
 * Attempts to convert the supplied String to an event region, with a fallback if necessary.
 */
private fun String?.toEventRegion(): EventRegion {
    return when (this) {
        "NA" -> EventRegion.NA
        "EU" -> EventRegion.EU
        "OCE" -> EventRegion.OCE
        "SAM" -> EventRegion.SAM
        "ASIA" -> EventRegion.ASIA
        "ME" -> EventRegion.ME
        "INT" -> EventRegion.INT
        else -> EventRegion.Unknown
    }
}

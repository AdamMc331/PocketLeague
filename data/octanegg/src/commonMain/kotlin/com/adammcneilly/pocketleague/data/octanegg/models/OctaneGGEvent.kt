package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to an Event from the octane.gg API.
 */
@Serializable
data class OctaneGGEvent(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("endDate")
    val endDateUTC: String? = null,
    @SerialName("image")
    val imageURL: String? = null,
    @SerialName("mode")
    val mode: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("prize")
    val prize: OctaneGGPrize? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("stages")
    val stages: List<OctaneGGStage>? = null,
    @SerialName("startDate")
    val startDateUTC: String? = null,
    @SerialName("tier")
    val tier: String? = null,
    @SerialName("groups")
    val groups: List<String>? = null,
    @SerialName("lan")
    val lan: Boolean? = null,
)

/**
 * Convert an [OctaneGGEvent] to an [Event] in our domain.
 */
fun OctaneGGEvent?.toEvent(): Event {
    val stages = this?.stages?.map(OctaneGGStage::toEventStage).orEmpty()

    val isLan = stages.any(EventStage::lan)

    val eventRegion = this?.region.toEventRegion()

    return Event(
        id = Event.Id(this?.id.orEmpty()),
        name = remapEventName(
            octaneEventName = this?.name.orEmpty(),
            region = eventRegion,
        ),
        startDateUTC = this?.startDateUTC,
        endDateUTC = this?.endDateUTC,
        imageURL = this?.imageURL,
        stages = stages,
        tier = this?.tier.toEventTier(),
        mode = this?.mode?.toString().orEmpty(),
        region = eventRegion,
        lan = isLan,
        prize = this?.prize?.toPrize(),
    )
}

/**
 * Given an [octaneEventName], check to see if this is a regional event, and if so, modify the event name to the Pocket League
 * preferred format of regional event names.
 *
 * Octane provides a name in the format: RLCS 2022-23 Winter North America Regional 3
 * We want to store it as: NA Winter Invitational
 *
 * SIDE NOTE: Ideally we still keep track of season info somehow, in case we ever want to fetch events for a specific
 * season. Removing it from the event name does create a little tech debt there.
 */
private fun remapEventName(
    octaneEventName: String,
    region: EventRegion,
): String {
    if (!octaneEventName.contains("regional", ignoreCase = true)) {
        return octaneEventName
    }

    val words = octaneEventName.split(" ")

    val splitName = words[2]
    val regionalName = when (words.last()) {
        "1" -> "Open"
        "2" -> "Cup"
        "3" -> "Invitational"
        else -> {
            println("Unable to properly parse regional: $octaneEventName")
            return octaneEventName
        }
    }
    val eventRegionAcronym = region.name

    return "$eventRegionAcronym $splitName $regionalName"
}

/**
 * Attempts to convert the supplied String to an event tier, with a fallback
 * if necessary.
 */
internal fun String?.toEventTier(): EventTier {
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
internal fun String?.toEventRegion(): EventRegion {
    return when (this) {
        "NA" -> EventRegion.NA
        "EU" -> EventRegion.EU
        "OCE" -> EventRegion.OCE
        "SAM" -> EventRegion.SAM
        "ASIA" -> EventRegion.APAC
        "ME" -> EventRegion.MENA
        "INT" -> EventRegion.INT
        "AF" -> EventRegion.SSA
        else -> EventRegion.Unknown
    }
}

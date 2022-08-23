package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
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
fun OctaneGGEvent.toEvent(): Event {
    return Event(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        stages = emptyList(), // FIX THIS ADAM
        tier = this.tier.toEventTier(),
        mode = this.mode?.toString().orEmpty(),
        region = this.region.toEventRegion(),
        lan = this.lan ?: false,
        prize = this.prize?.toPrize(),
    )
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
        "ASIA" -> EventRegion.ASIA
        "ME" -> EventRegion.ME
        "INT" -> EventRegion.INT
        else -> EventRegion.Unknown
    }
}

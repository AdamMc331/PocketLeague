package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import com.adammcneilly.pocketleague.shared.app.core.models.Event
import com.adammcneilly.pocketleague.shared.app.core.models.EventStage
import com.adammcneilly.pocketleague.shared.app.core.models.Region
import com.adammcneilly.pocketleague.shared.app.data.octanegg.OctaneGGEventTierMapper
import com.adammcneilly.pocketleague.shared.app.data.octanegg.OctaneGGRegionMapper
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
) {
    /**
     * Convert an [OctaneGGEvent] to an [Event] in our domain.
     */
    fun toEvent(): Event {
        val stages = this.stages?.map(OctaneGGStage::toEventStage).orEmpty()

        val isLan = stages.any(EventStage::lan)

        val eventRegion = OctaneGGRegionMapper.fromString(this.region.orEmpty())

        return Event(
            id = this.id.orEmpty(),
            name = remapEventName(
                octaneEventName = this.name.orEmpty(),
                region = eventRegion,
            ),
            startDateUTC = this.startDateUTC,
            endDateUTC = this.endDateUTC,
            imageURL = this.imageURL,
            stages = stages,
            tier = OctaneGGEventTierMapper.fromString(this.tier.orEmpty()),
            mode = this.mode?.toString().orEmpty(),
            region = eventRegion,
            lan = isLan,
            prize = this.prize?.toPrize(),
        )
    }
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
    region: Region,
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

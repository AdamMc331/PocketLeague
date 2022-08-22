package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
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
    val endDate: String? = null,
    @SerialName("image")
    val image: String? = null,
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
    val startDate: String? = null,
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
        startDateUTC = this.startDate.orEmpty(),
        endDateUTC = this.endDate.orEmpty(),
        imageUrl = this.image,
        // FIX THE REST ADAM
        stages = emptyList(),
        tier = EventTier.Unknown,
        mode = this.mode.toString(),
        region = EventRegion.Unknown,
        lan = this.lan ?: false,
        prize = Prize(0.0, ""),
    )
}

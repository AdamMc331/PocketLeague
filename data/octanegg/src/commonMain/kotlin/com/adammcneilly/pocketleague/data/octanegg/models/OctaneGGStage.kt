package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.EventStage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a stage from the octane.gg API.
 */
@Serializable
data class OctaneGGStage(
    @SerialName("_id")
    val id: Int? = null,
    @SerialName("endDate")
    val endDateUTC: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("startDate")
    val startDateUTC: String? = null,
    @SerialName("prize")
    val prize: OctaneGGPrize? = null,
    @SerialName("location")
    val location: OctaneGGLocation? = null,
    @SerialName("lan")
    val lan: Boolean? = null,
    @SerialName("liquipedia")
    val liquipedia: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("qualifier")
    val qualifier: Boolean? = null,
)

/**
 * Converts an [OctaneGGStage] to an [EventStage] in our domain.
 */
fun OctaneGGStage?.toEventStage(): EventStage {
    return EventStage(
        id = this?.id?.toString().orEmpty(),
        name = this?.name.orEmpty(),
        region = this?.region.orEmpty(),
        startDateUTC = this?.startDateUTC,
        endDateUTC = this?.endDateUTC,
        liquipedia = this?.liquipedia.orEmpty(),
        qualifier = this?.qualifier ?: false,
        lan = this?.lan ?: false,
        location = this?.location?.toLocation(),
    )
}

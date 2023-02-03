package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Format
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines the format of a match between two teams.
 */
@Serializable
data class OctaneGGFormat(
    @SerialName("type")
    val type: String? = null,
    @SerialName("length")
    val length: Int? = null,
)

/**
 * Convert a format from the octane.gg domain to ours.
 */
fun OctaneGGFormat?.toFormat(): Format {
    return Format(
        type = this?.type.orEmpty(),
        length = this?.length ?: 0,
    )
}

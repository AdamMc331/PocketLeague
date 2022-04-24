package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines a format of a match between two teams.
 *
 * @property[length] The length of the format, so in a best of 7, this value would be seven.
 * @property[type] The type of format, for example "best" would mean a best of X series.
 */
@Serializable
data class OctaneGGMatchFormat(
    @SerialName("length")
    val length: Int? = null,
    @SerialName("type")
    val type: String? = null
)

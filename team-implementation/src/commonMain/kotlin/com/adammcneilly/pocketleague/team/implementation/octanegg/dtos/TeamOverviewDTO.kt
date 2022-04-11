package com.adammcneilly.pocketleague.team.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Overview information about a team, that does not include roster info.
 */
@Serializable
internal data class TeamOverviewDTO(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("relevant")
    val relevant: Boolean? = null,
    @SerialName("slug")
    val slug: String? = null,
)

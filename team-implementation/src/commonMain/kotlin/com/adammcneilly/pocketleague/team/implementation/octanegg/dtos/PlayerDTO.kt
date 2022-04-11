package com.adammcneilly.pocketleague.team.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a person who is a player for a [TeamDTO] in the octane.gg API.
 */
@Serializable
internal data class PlayerDTO(
    @SerialName("accounts")
    val accounts: List<AccountDTO>? = null,
    @SerialName("coach")
    val coach: Boolean? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("_id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("relevant")
    val relevant: Boolean? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("substitute")
    val substitute: Boolean? = null,
    @SerialName("tag")
    val tag: String? = null,
    @SerialName("team")
    val team: TeamOverviewDTO? = null,
)

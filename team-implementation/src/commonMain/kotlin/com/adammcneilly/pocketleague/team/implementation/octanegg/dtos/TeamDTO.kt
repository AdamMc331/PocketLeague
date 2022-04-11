package com.adammcneilly.pocketleague.team.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a team within the octane.gg API domain.
 */
@Serializable
internal data class TeamDTO(
    @SerialName("players")
    val players: List<PlayerDTO>? = null,
    @SerialName("team")
    val team: TeamOverviewDTO? = null,
)

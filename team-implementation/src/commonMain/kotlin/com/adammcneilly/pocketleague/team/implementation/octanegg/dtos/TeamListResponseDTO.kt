package com.adammcneilly.pocketleague.team.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data response for a list of teams within the octane.gg API.
 */
@Serializable
internal data class TeamListResponseDTO(
    @SerialName("teams")
    val teams: List<TeamDTO>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("perPage")
    val perPage: Int? = null,
    @SerialName("pageSize")
    val pageSize: Int? = null,
)

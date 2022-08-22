package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Overview information about a team, that does not include roster info.
 */
@Serializable
data class OctaneGGTeamOverview(
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

/**
 * Converts an [OctaneGGTeamDetail] entity to a [Team] in our domain.
 */
fun OctaneGGTeamOverview.toTeam(): Team {
    return Team(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
    )
}

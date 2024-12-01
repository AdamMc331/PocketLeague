package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import com.adammcneilly.pocketleague.shared.app.core.models.Team
import com.adammcneilly.pocketleague.shared.app.data.octanegg.OctaneGGRegionMapper
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
) {
    /**
     * Converts an [OctaneGGTeamDetail] entity to a [Team] in our domain.
     */
    fun toTeam(): Team {
        return Team(
            id = this.id.orEmpty(),
            // This is sus??
            name = this.name ?: "TBD",
            lightThemeImageURL = this.image,
            region = OctaneGGRegionMapper.fromString(this.region.orEmpty()),
        )
    }
}

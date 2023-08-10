package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Player
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a person who is a player for a [TeamDTO] in the octane.gg API.
 */
@Serializable
data class OctaneGGPlayer(
    @SerialName("accounts")
    val accounts: List<OctaneGGAccount>? = null,
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
    val team: OctaneGGTeamOverview? = null,
)

/**
 * Converts an [OctaneGGPlayer] to a [Player].
 */
fun OctaneGGPlayer.toPlayer(): Player {
    return Player(
        id = this.id.orEmpty(),
        slug = this.slug.orEmpty(),
        tag = this.tag.orEmpty(),
        countryCode = this.country.orEmpty(),
        name = this.name.orEmpty(),
        currentTeamId = team?.id.orEmpty(),
        isCoach = this.coach == true,
    )
}

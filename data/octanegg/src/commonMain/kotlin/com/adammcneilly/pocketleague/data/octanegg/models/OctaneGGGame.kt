package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.GameTeamResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A game played within a [OctaneGGMatch].
 */
@Serializable
data class OctaneGGGame(
    @SerialName("ballchasing")
    val ballchasing: String? = null,
    @SerialName("blue")
    val blue: OctaneGGGameTeamResult? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("_id")
    val id: String? = null,
    @SerialName("map")
    val map: OctaneGGMap? = null,
    @SerialName("match")
    val match: OctaneGGMatch? = null,
    @SerialName("number")
    val number: Int? = null,
    @SerialName("octane_id")
    val octaneId: String? = null,
    @SerialName("orange")
    val orange: OctaneGGGameTeamResult? = null,
)

/**
 * Converts an [OctaneGGGame] to a [Game] in our domain.
 */
fun OctaneGGGame.toGame(): Game {
    // Currently the octane.gg api does not include a map name for
    // this map ID, so let's override it ourselves.
    val mapName = when (this.map?.id) {
        "outlaw_oasis_p" -> "Deadeye Canyon (Oasis)"
        else -> this.map?.name.orEmpty()
    }

    return Game(
        id = this.id.orEmpty(),
        blue = this.blue?.toGameTeamResult() ?: GameTeamResult(),
        orange = this.orange?.toGameTeamResult() ?: GameTeamResult(),
        map = mapName,
        number = this.number ?: 0,
        duration = this.duration ?: Game.GAME_DEFAULT_DURATION_SECONDS,
    )
}

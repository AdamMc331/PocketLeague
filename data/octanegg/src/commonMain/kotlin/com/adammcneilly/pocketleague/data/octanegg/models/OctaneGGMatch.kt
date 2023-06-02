package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.StageRound
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a match between two teams, [blue] and [orange].
 */
@Serializable
data class OctaneGGMatch(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("event")
    val event: OctaneGGEvent? = null,
    @SerialName("date")
    val dateUTC: String? = null,
    @SerialName("blue")
    val blue: OctaneGGMatchTeamResult? = null,
    @SerialName("orange")
    val orange: OctaneGGMatchTeamResult? = null,
    @SerialName("stage")
    val stage: OctaneGGStage? = null,
    @SerialName("format")
    val format: OctaneGGFormat? = null,
    @SerialName("games")
    val games: List<OctaneGGGameOverview>? = null,
)

/**
 * Converts an [OctaneGGMatch] to a [Match] in our domain.
 */
fun OctaneGGMatch.toMatch(): Match {
    return Match(
        id = this.id.orEmpty(),
        event = this.event.toEvent(),
        dateUTC = this.dateUTC,
        blueTeam = this.blue.toMatchTeamResult(),
        orangeTeam = this.orange.toMatchTeamResult(),
        stage = this.stage.toEventStage(),
        format = this.format.toFormat(),
        gameOverviews = this.games?.map(OctaneGGGameOverview::toGameOverview).orEmpty(),
        // Octane.GG API has no concept of a stage round, so we'll just return a default here.
        round = StageRound(0, ""),
    )
}

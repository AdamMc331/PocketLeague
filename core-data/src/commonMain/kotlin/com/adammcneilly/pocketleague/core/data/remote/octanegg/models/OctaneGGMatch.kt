package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

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

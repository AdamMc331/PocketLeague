package com.adammcneilly.pocketleague.phase.domain.models

import com.adammcneilly.pocketleague.bracket.domain.models.BracketType

/**
 * A [Phase] is a portion of an event. An example could be a Swiss stage and a Bracket stage. They'll
 * be sorted by the [phaseOrder] property.
 */
data class Phase(
    val numPools: Int,
    val numParticipants: Int,
    val name: String,
    val phaseOrder: Int,
    val bracketType: BracketType?,
)

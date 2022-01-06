package com.adammcneilly.pocketleague.phase.domain.models

import com.adammcneilly.pocketleague.bracket.domain.models.BracketType

/**
 * A [PhaseOverview] is a portion of an event. An example could be a Swiss stage and a Bracket stage. They'll
 * be sorted by the [phaseOrder] property.
 */
data class PhaseOverview(
    val id: String,
    val groupId: String,
    val numPools: Int,
    val numEntrants: Int,
    val name: String,
    val phaseOrder: Int,
    val bracketType: BracketType,
)
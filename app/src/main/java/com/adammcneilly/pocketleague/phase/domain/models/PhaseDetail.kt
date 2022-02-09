package com.adammcneilly.pocketleague.phase.domain.models

import com.adammcneilly.pocketleague.models.BracketType
import com.adammcneilly.pocketleague.set.domain.models.EventSet

/**
 * A [PhaseDetail] is a portion of an event. An example could be a Swiss stage and a Bracket stage. They'll
 * be sorted by the [phaseOrder] property.
 */
data class PhaseDetail(
    val id: String,
    val groupId: String,
    val numPools: Int,
    val numEntrants: Int,
    val name: String,
    val phaseOrder: Int,
    val bracketType: BracketType,
    val sets: List<EventSet>,
)

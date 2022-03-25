package com.adammcneilly.pocketleague.shared.core.models

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

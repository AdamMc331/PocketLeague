package com.adammcneilly.pocketleague.phase.ui

import com.adammcneilly.pocketleague.phase.domain.models.Phase

/**
 * A user friendly representation of a [Phase] to be displayed on the UI.
 */
data class PhaseDisplayModel(
    val phaseName: String,
    val numPools: String,
    val bracketType: String,
    val numEntrants: String,
)

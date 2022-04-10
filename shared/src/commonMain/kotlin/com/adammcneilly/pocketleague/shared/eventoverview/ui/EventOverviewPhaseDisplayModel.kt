package com.adammcneilly.pocketleague.shared.eventoverview.ui

import com.adammcneilly.pocketleague.core.models.PhaseOverview

/**
 * A user friendly representation of a [PhaseOverview] to be displayed on the UI.
 */
data class EventOverviewPhaseDisplayModel(
    val phaseId: String,
    val phaseName: String,
    val numPools: String,
    val bracketType: String,
    val numEntrants: String,
)

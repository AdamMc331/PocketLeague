package com.adammcneilly.pocketleague.phase.ui

import com.adammcneilly.pocketleague.core.models.PhaseOverview

/**
 * A user friendly representation of a [PhaseOverview] to be displayed on the UI.
 */
data class PhaseDisplayModel(
    val phaseName: String,
    val numPools: String,
    val bracketType: String,
    val numEntrants: String,
    val onClick: () -> Unit,
) {

    /**
     * This is needed for testing just until we can remove onClick.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhaseDisplayModel

        if (phaseName != other.phaseName) return false
        if (numPools != other.numPools) return false
        if (bracketType != other.bracketType) return false
        if (numEntrants != other.numEntrants) return false

        return true
    }
}

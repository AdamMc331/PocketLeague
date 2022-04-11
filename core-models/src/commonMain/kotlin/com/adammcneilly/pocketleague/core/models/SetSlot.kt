package com.adammcneilly.pocketleague.core.models

/**
 * An entry within a [EventSet] that contains the [team] and their respective [slotIndex].
 */
data class SetSlot(
    val team: Team,
    val slotIndex: Int,
)

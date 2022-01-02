package com.adammcneilly.pocketleague.set.domain.models

import com.adammcneilly.pocketleague.core.domain.models.Team

/**
 * An entry within a [EventSet] that contains the [team] and their respective [slotIndex].
 */
data class SetSlot(
    val team: Team,
    val slotIndex: Int,
)

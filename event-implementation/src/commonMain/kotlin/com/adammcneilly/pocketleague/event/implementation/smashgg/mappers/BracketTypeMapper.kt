package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.BracketType

/**
 * Converts an [ApolloBracketType] from the smash.gg API to the [BracketType] within our domain.
 */
fun com.adammcneilly.pocketleague.event.implementation.graphql.type.BracketType.toBracketType(): BracketType {
    return when (this) {
        com.adammcneilly.pocketleague.event.implementation.graphql.type.BracketType.CUSTOM_SCHEDULE -> BracketType.CUSTOM
        com.adammcneilly.pocketleague.event.implementation.graphql.type.BracketType.SINGLE_ELIMINATION -> BracketType.SINGLE_ELIMINATION
        com.adammcneilly.pocketleague.event.implementation.graphql.type.BracketType.DOUBLE_ELIMINATION -> BracketType.DOUBLE_ELIMINATION
        else -> BracketType.UNKNOWN
    }
}

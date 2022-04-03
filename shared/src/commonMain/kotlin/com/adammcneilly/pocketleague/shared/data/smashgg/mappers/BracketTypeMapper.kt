package com.adammcneilly.pocketleague.shared.data.smashgg.mappers

import com.adammcneilly.pocketleague.shared.core.models.BracketType

typealias ApolloBracketType = com.adammcneilly.pocketleague.shared.graphql.type.BracketType

fun ApolloBracketType.toBracketType(): BracketType {
    return when (this) {
        ApolloBracketType.CUSTOM_SCHEDULE -> BracketType.CUSTOM
        ApolloBracketType.SINGLE_ELIMINATION -> BracketType.SINGLE_ELIMINATION
        ApolloBracketType.DOUBLE_ELIMINATION -> BracketType.DOUBLE_ELIMINATION
        else -> BracketType.UNKNOWN
    }
}

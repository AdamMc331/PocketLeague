package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.PhaseGroupFragment

fun PhaseGroupFragment?.toPhase(): PhaseOverview {
    val overview = this?.phase?.phaseOverviewFragment

    return PhaseOverview(
        id = overview?.id.orEmpty(),
        groupId = this?.id.orEmpty(),
        numPools = overview?.groupCount ?: 0,
        numEntrants = overview?.numSeeds ?: 0,
        name = overview?.name.orEmpty(),
        bracketType = this?.bracketType?.toBracketType()
            ?: BracketType.UNKNOWN,
        phaseOrder = overview?.phaseOrder ?: 0,
    )
}

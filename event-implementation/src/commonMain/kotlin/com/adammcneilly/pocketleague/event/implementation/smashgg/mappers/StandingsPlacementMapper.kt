package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.StandingsPlacementFragment

fun StandingsPlacementFragment?.toStandingsPlacement(): StandingsPlacement {
    return StandingsPlacement(
        placement = this?.placement ?: 0,
        team = this?.entrant?.eventEntrantFragment.let(EventEntrantFragment?::toTeam),
    )
}

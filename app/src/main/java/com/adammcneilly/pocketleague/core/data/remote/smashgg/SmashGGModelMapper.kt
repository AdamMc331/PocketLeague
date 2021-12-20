package com.adammcneilly.pocketleague.core.data.remote.smashgg

import com.adammcneilly.pocketleague.bracket.domain.models.BracketType
import com.adammcneilly.pocketleague.core.domain.models.Player
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.event.data.remote.ApolloBracketType
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.fragment.EventPlayerFragment
import com.adammcneilly.pocketleague.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.fragment.StandingsPlacementFragment
import com.adammcneilly.pocketleague.phase.domain.models.Phase
import com.adammcneilly.pocketleague.standings.domain.models.Standings
import com.adammcneilly.pocketleague.standings.domain.models.StandingsPlacement
import com.apollographql.apollo.api.BigDecimal
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

/**
 * A helper class that maps smash.gg DTO models to a corresponding Pocket League domain model.
 */
class SmashGGModelMapper @Inject constructor() {
    /**
     * Convert the supplied [eventOverview] to an [EventOverview] entity.
     */
    fun eventOverviewFragmentToEventOverview(eventOverview: EventOverviewFragment?): EventOverview {
        val startSeconds = (eventOverview?.startAt as BigDecimal).toLong()
        val startDate = Instant.ofEpochSecond(startSeconds).atOffset(ZoneOffset.UTC)

        return EventOverview(
            name = eventOverview.name.orEmpty(),
            phases = eventOverview.phaseGroups
                ?.mapNotNull {
                    it?.fragments?.phaseGroupFragment.let(PhaseGroupFragment?::toPhase)
                }
                ?.sortedBy {
                    it.phaseOrder
                }
                ?.distinctBy { phase ->
                    phase.phaseOrder
                }
                .orEmpty(),
            startDate = startDate.toZonedDateTime(),
            standings = Standings(
                placements = eventOverview.standings
                    ?.nodes
                    ?.mapNotNull { node ->
                        node
                            ?.fragments
                            ?.standingsPlacementFragment
                            .let(StandingsPlacementFragment?::toStandingsPlacement)
                    }
                    .orEmpty()
            ),
        )
    }
}

private fun EventPlayerFragment?.toPlayer(): Player {
    return Player(
        countryCode = "",
        gamerTag = this?.gamerTag.orEmpty(),
        realName = "",
    )
}

private fun EventEntrantFragment?.toTeam(): Team {
    return Team(
        name = this?.name.orEmpty(),
        lightThemeLogoImageUrl = this?.team?.images?.firstOrNull()?.url,
        darkThemeLogoImageUrl = this?.team?.images?.firstOrNull()?.url,
        roster = this?.team?.members?.mapNotNull { member ->
            member?.player?.fragments?.eventPlayerFragment.let(EventPlayerFragment?::toPlayer)
        }.orEmpty(),
    )
}

private fun StandingsPlacementFragment?.toStandingsPlacement(): StandingsPlacement {
    return StandingsPlacement(
        placement = this?.placement ?: 0,
        team = this?.entrant?.fragments?.eventEntrantFragment.let(EventEntrantFragment?::toTeam),
    )
}

private fun PhaseGroupFragment?.toPhase(): Phase {
    return Phase(
        id = this?.phase?.id.orEmpty(),
        groupId = this?.id.orEmpty(),
        numPools = this?.phase?.groupCount ?: 0,
        numEntrants = this?.phase?.numSeeds ?: 0,
        name = this?.phase?.name.orEmpty(),
        bracketType = this?.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
        phaseOrder = this?.phase?.phaseOrder ?: 0,
    )
}

private fun ApolloBracketType.toBracketType(): BracketType {
    return when (this) {
        ApolloBracketType.CUSTOM_SCHEDULE -> BracketType.CUSTOM
        ApolloBracketType.SINGLE_ELIMINATION -> BracketType.SINGLE_ELIMINATION
        ApolloBracketType.DOUBLE_ELIMINATION -> BracketType.DOUBLE_ELIMINATION
        else -> BracketType.UNKNOWN
    }
}

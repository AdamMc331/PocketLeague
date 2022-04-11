package com.adammcneilly.pocketleague.core.data.remote.smashgg

import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSet
import com.adammcneilly.pocketleague.core.models.PhaseDetail
import com.adammcneilly.pocketleague.core.models.PhaseOverview
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.SetSlot
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.core.models.StandingsPlacement
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.graphql.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.graphql.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.graphql.fragment.EventPlayerFragment
import com.adammcneilly.pocketleague.graphql.fragment.EventSetFragment
import com.adammcneilly.pocketleague.graphql.fragment.PhaseDetailFragment
import com.adammcneilly.pocketleague.graphql.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.graphql.fragment.SetSlotFragment
import com.adammcneilly.pocketleague.graphql.fragment.StandingsPlacementFragment
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

typealias ApolloBracketType = com.adammcneilly.pocketleague.graphql.type.BracketType

/**
 * A helper class that maps smash.gg DTO models to a corresponding Pocket League domain model.
 */
class SmashGGModelMapper @Inject constructor() {
    /**
     * Convert the supplied [eventOverview] to an [EventOverview] entity.
     */
    fun eventOverviewFragmentToEventOverview(eventOverview: EventOverviewFragment?): EventOverview {
        val eventTimeZone = TimeZone.UTC
        val startSeconds = (eventOverview?.eventSummaryFragment?.startAt as Int).toLong()
        val startDate = Instant.fromEpochSeconds(startSeconds).toLocalDateTime(eventTimeZone)

        return EventOverview(
            name = eventOverview.eventSummaryFragment.name.orEmpty(),
            phases = eventOverview.phaseGroups
                ?.mapNotNull {
                    it?.phaseGroupFragment.let(PhaseGroupFragment?::toPhase)
                }
                ?.sortedBy {
                    it.phaseOrder
                }
                ?.distinctBy { phase ->
                    phase.phaseOrder
                }
                .orEmpty(),
            startDate = startDate,
            timeZone = eventTimeZone,
            standings = Standings(
                placements = eventOverview.standings
                    ?.nodes
                    ?.mapNotNull { node ->
                        node
                            ?.standingsPlacementFragment
                            .let(StandingsPlacementFragment?::toStandingsPlacement)
                    }
                    .orEmpty()
            ),
        )
    }

    /**
     * Convert the supplied [phaseDetail] to a [PhaseDetail] entity.
     */
    fun phaseDetailFragmentToPhaseDetail(phaseDetail: PhaseDetailFragment?): PhaseDetail {
        val phaseOverview = phaseDetail?.phaseOverviewFragment

        return PhaseDetail(
            id = phaseOverview?.id.orEmpty(),
            groupId = "",
            numPools = phaseOverview?.groupCount ?: 0,
            numEntrants = phaseOverview?.numSeeds ?: 0,
            name = phaseOverview?.name.orEmpty(),
            phaseOrder = phaseOverview?.phaseOrder ?: 0,
            bracketType = phaseOverview?.bracketType?.toBracketType()
                ?: BracketType.UNKNOWN,
            sets = phaseDetail?.sets?.nodes?.mapNotNull { set ->
                set?.eventSetFragment.toEventSet()
            }.orEmpty(),
        )
    }
}

private fun EventSetFragment?.toEventSet(): EventSet {
    return EventSet(
        fullRoundText = this?.fullRoundText.orEmpty(),
        displayScore = this?.displayScore.orEmpty(),
        round = this?.round.toString(),
        winnerId = this?.winnerId.toString(),
        slots = this?.slots?.mapNotNull { setSlot ->
            setSlot?.setSlotFragment?.toSetSlot()
        }.orEmpty(),
        games = emptyList(),
    )
}

private fun SetSlotFragment?.toSetSlot(): SetSlot {
    return SetSlot(
        team = this?.entrant?.eventEntrantFragment.toTeam(),
        slotIndex = this?.slotIndex ?: 0,
    )
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
            member?.player?.eventPlayerFragment.let(EventPlayerFragment?::toPlayer)
        }.orEmpty(),
    )
}

private fun StandingsPlacementFragment?.toStandingsPlacement(): StandingsPlacement {
    return StandingsPlacement(
        placement = this?.placement ?: 0,
        team = this?.entrant?.eventEntrantFragment.let(EventEntrantFragment?::toTeam),
    )
}

private fun PhaseGroupFragment?.toPhase(): PhaseOverview {
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

private fun ApolloBracketType.toBracketType(): BracketType {
    return when (this) {
        ApolloBracketType.CUSTOM_SCHEDULE -> BracketType.CUSTOM
        ApolloBracketType.SINGLE_ELIMINATION -> BracketType.SINGLE_ELIMINATION
        ApolloBracketType.DOUBLE_ELIMINATION -> BracketType.DOUBLE_ELIMINATION
        else -> BracketType.UNKNOWN
    }
}

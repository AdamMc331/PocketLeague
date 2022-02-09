package com.adammcneilly.pocketleague.core.data.remote.smashgg

import com.adammcneilly.pocketleague.event.data.remote.ApolloBracketType
import com.adammcneilly.pocketleague.fragment.EventEntrantFragment
import com.adammcneilly.pocketleague.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.fragment.EventPlayerFragment
import com.adammcneilly.pocketleague.fragment.EventSetFragment
import com.adammcneilly.pocketleague.fragment.PhaseDetailFragment
import com.adammcneilly.pocketleague.fragment.PhaseGroupFragment
import com.adammcneilly.pocketleague.fragment.SetSlotFragment
import com.adammcneilly.pocketleague.fragment.StandingsPlacementFragment
import com.adammcneilly.pocketleague.models.BracketType
import com.adammcneilly.pocketleague.models.EventOverview
import com.adammcneilly.pocketleague.models.PhaseOverview
import com.adammcneilly.pocketleague.models.Player
import com.adammcneilly.pocketleague.models.Standings
import com.adammcneilly.pocketleague.models.StandingsPlacement
import com.adammcneilly.pocketleague.models.Team
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail
import com.adammcneilly.pocketleague.set.domain.models.EventSet
import com.adammcneilly.pocketleague.set.domain.models.SetSlot
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
        val startSeconds = (eventOverview?.fragments?.eventSummaryFragment?.startAt as BigDecimal).toLong()
        val startDate = Instant.ofEpochSecond(startSeconds).atOffset(ZoneOffset.UTC)

        return EventOverview(
            name = eventOverview.fragments.eventSummaryFragment.name.orEmpty(),
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
            startDateEpochSeconds = startDate.toZonedDateTime(),
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

    /**
     * Convert the supplied [phaseDetail] to a [PhaseDetail] entity.
     */
    fun phaseDetailFragmentToPhaseDetail(phaseDetail: PhaseDetailFragment?): PhaseDetail {
        val phaseOverview = phaseDetail?.fragments?.phaseOverviewFragment

        return PhaseDetail(
            id = phaseOverview?.id.orEmpty(),
            groupId = "",
            numPools = phaseOverview?.groupCount ?: 0,
            numEntrants = phaseOverview?.numSeeds ?: 0,
            name = phaseOverview?.name.orEmpty(),
            phaseOrder = phaseOverview?.phaseOrder ?: 0,
            bracketType = phaseOverview?.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
            sets = phaseDetail?.sets?.nodes?.mapNotNull { set ->
                set?.fragments?.eventSetFragment.toEventSet()
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
            setSlot?.fragments?.setSlotFragment?.toSetSlot()
        }.orEmpty(),
    )
}

private fun SetSlotFragment?.toSetSlot(): SetSlot {
    return SetSlot(
        team = this?.entrant?.fragments?.eventEntrantFragment.toTeam(),
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

private fun PhaseGroupFragment?.toPhase(): PhaseOverview {
    val overview = this?.phase?.fragments?.phaseOverviewFragment

    return PhaseOverview(
        id = overview?.id.orEmpty(),
        groupId = this?.id.orEmpty(),
        numPools = overview?.groupCount ?: 0,
        numEntrants = overview?.numSeeds ?: 0,
        name = overview?.name.orEmpty(),
        bracketType = this?.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
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

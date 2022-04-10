package com.adammcneilly.pocketleague.shared.data.phase.remote.smashgg

import com.adammcneilly.pocketleague.core.models.BracketType
import com.adammcneilly.pocketleague.core.models.EventSet
import com.adammcneilly.pocketleague.core.models.PhaseDetail
import com.adammcneilly.pocketleague.core.models.SetSlot
import com.adammcneilly.pocketleague.shared.data.Result
import com.adammcneilly.pocketleague.shared.data.phase.PhaseRepository
import com.adammcneilly.pocketleague.shared.data.smashgg.mappers.toBracketType
import com.adammcneilly.pocketleague.shared.data.smashgg.mappers.toTeam
import com.adammcneilly.pocketleague.shared.data.smashgg.smashGGApolloClient
import com.adammcneilly.pocketleague.shared.graphql.PhaseDetailQuery
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventSetFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.PhaseDetailFragment
import com.adammcneilly.pocketleague.shared.graphql.fragment.SetSlotFragment
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A concrete implementation of a [PhaseRepository] that requests information from the Smash.gg
 * API.
 */
class SmashGGPhaseService : PhaseRepository {

    override suspend fun fetchPhaseDetail(phaseId: String): Flow<Result<PhaseDetail>> {
        val query = PhaseDetailQuery(
            id = Optional.presentIfNotNull(phaseId),
        )

        val response = smashGGApolloClient.query(query).toFlow()

        return response.map { dataResponse ->
            val phaseDetail = dataResponse
                .data
                ?.phase
                ?.phaseDetailFragment
                ?.toPhaseDetail()

            if (phaseDetail != null) {
                Result.Success(phaseDetail)
            } else {
                Result.Error(Throwable("Unable to fetch phase detail: $phaseId"))
            }
        }
    }
}

private fun PhaseDetailFragment.toPhaseDetail(): PhaseDetail {
    val overviewFragment = this.phaseOverviewFragment

    return PhaseDetail(
        id = overviewFragment.id.orEmpty(),
        groupId = "TODO: NOT IMPLEMENTED",
        numPools = overviewFragment.groupCount ?: 0,
        numEntrants = overviewFragment.numSeeds ?: 0,
        name = overviewFragment.name.orEmpty(),
        phaseOrder = overviewFragment.phaseOrder ?: 0,
        bracketType = overviewFragment.bracketType?.toBracketType() ?: BracketType.UNKNOWN,
        sets = this.sets?.nodes?.mapNotNull { setNode ->
            setNode?.eventSetFragment?.toEventSet()
        }.orEmpty(),
    )
}

private fun EventSetFragment.toEventSet(): EventSet {
    return EventSet(
        fullRoundText = this.fullRoundText.orEmpty(),
        displayScore = this.displayScore.orEmpty(),
        round = this.round?.toString().orEmpty(),
        winnerId = this.winnerId?.toString().orEmpty(),
        slots = this.slots?.mapNotNull { setSlotNode ->
            setSlotNode?.setSlotFragment?.toSetSlot()
        }.orEmpty(),
        // Games coming soon
        games = emptyList(),
    )
}

private fun SetSlotFragment.toSetSlot(): SetSlot {
    return SetSlot(
        team = this.entrant?.eventEntrantFragment?.toTeam()!!,
        slotIndex = this.slotIndex ?: 0,
    )
}

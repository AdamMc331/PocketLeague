package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.models.Format
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.fragment.SetFragment
import kotlinx.datetime.Instant

/**
 * Convert a [SetFragment] GQL model into a [Match] entity from the pocket league domain.
 */
fun SetFragment.toMatch(): Match {
    val startDateUTC = (this.startAt as? Int)?.let { startAt ->
        Instant.fromEpochSeconds(startAt.toLong()).toString()
    }

    val orderedSlots = this.slots?.sortedBy { it?.slotIndex }!!
    val orangeSlot = orderedSlots[0]!!
    val blueSlot = orderedSlots[1]!!

    val orangeTeamWins = this.games?.count {
        it?.winnerId?.toString() == orangeSlot.entrant?.team?.teamFragment?.id
    } ?: 0

    val blueTeamWins = this.games?.size?.minus(orangeTeamWins) ?: 0

    return Match(
        id = this.id.orEmpty(),
        event = this.event?.tournament?.tournamentFragment?.toEvent()!!,
        dateUTC = startDateUTC,
        blueTeam = MatchTeamResult(
            score = blueTeamWins,
            winner = (blueSlot.entrant?.team?.teamFragment?.id == this.winnerId?.toString()),
            team = blueSlot.entrant?.team?.teamFragment?.toTeam() ?: Team(),
            players = emptyList(),
            stats = null,
        ),
        orangeTeam = MatchTeamResult(
            score = orangeTeamWins,
            winner = (orangeSlot.entrant?.team?.teamFragment?.id == this.winnerId?.toString()),
            team = orangeSlot.entrant?.team?.teamFragment?.toTeam() ?: Team(),
            players = emptyList(),
            stats = null,
        ),
        stage = this.event.eventFragment.toEventStage(),
        format = Format(),
        gameOverviews = emptyList(),
    )
}

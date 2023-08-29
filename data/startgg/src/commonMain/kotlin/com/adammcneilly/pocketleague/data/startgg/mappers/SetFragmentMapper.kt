package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Format
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult
import com.adammcneilly.pocketleague.core.models.StageRound
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.fragment.SetFragment

/**
 * Convert a [SetFragment] GQL model into a [Match] entity from the pocket league domain.
 */
fun SetFragment.toMatch(timeProvider: TimeProvider): Match {
    val startDateUTC = (this.startAt as? Int)?.let { startAt ->
        timeProvider.fromEpochSeconds(startAt.toLong())
    }

    val orderedSlots = this.slots?.sortedBy { it?.slotIndex }!!
    val orangeSlot = orderedSlots[0]!!
    val blueSlot = orderedSlots[1]!!

    // The only way to get the number of wins for each team is to loop through all of the games, and
    // check based on winnerID.
    // First we calculate the number of games won by the orange team, then we subtract that from the total
    // number of games to calculate how many games were won by the blue team.
    val orangeTeamWins = this.games?.count {
        it?.winnerId?.toString() == orangeSlot.entrant?.team?.teamFragment?.id
    } ?: 0
    val blueTeamWins = this.games?.size?.minus(orangeTeamWins) ?: 0

    return Match(
        id = Match.Id(this.id.orEmpty()),
        event = this.event?.tournament?.tournamentFragment?.toEvent(timeProvider)!!,
        dateUTC = startDateUTC,
        blueTeam = blueSlot.toMatchTeamResult(
            teamWins = blueTeamWins,
            winnerId = this.winnerId,
        ),
        orangeTeam = orangeSlot.toMatchTeamResult(
            teamWins = orangeTeamWins,
            winnerId = this.winnerId,
        ),
        stage = this.event.eventFragment.toEventStage(timeProvider),
        // We should be able to get this information from the API,
        // we just need to add more to this mapping function.
        format = Format(),
        gameOverviews = emptyList(),
        round = StageRound(
            number = this.round ?: 0,
            name = this.fullRoundText.orEmpty(),
        ),
    )
}

private fun SetFragment.Slot.toMatchTeamResult(
    teamWins: Int,
    winnerId: Int?,
): MatchTeamResult {
    val team = this.entrant?.team?.teamFragment?.toTeam() ?: Team()

    return MatchTeamResult(
        score = teamWins,
        winner = (team.id == winnerId.toString()),
        team = team,
        players = emptyList(),
        stats = null,
    )
}

package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.shared.models.Match
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [OctaneGGMatch] to a [Match] entity.
 */
fun OctaneGGMatch.toMatch(): Match {
    return Match(
        id = this.id!!,
        event = this.event?.toEvent()!!,
        date = this.date?.let { date ->
            Instant.parse(date)
        }?.toLocalDateTime(TimeZone.UTC),
        blueTeam = this.blue?.toMatchTeamResult()!!,
        orangeTeam = this.orange?.toMatchTeamResult()!!,
    )
}

package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGMatch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [OctaneGGMatch] to a [Match] entity.
 */
fun OctaneGGMatch.toMatch(): Match? {
    // Right now we just filter out items with invalid data. We need a way to log
    // or notify of this in the future.
    @Suppress("ComplexCondition")
    if (
        this.id == null ||
        this.event == null ||
        this.blue == null ||
        this.orange == null
    ) {
        return null
    }

    return Match(
        id = this.id,
        event = this.event.toEvent(),
        date = this.date?.let { date ->
            Instant.parse(date)
        }?.toLocalDateTime(TimeZone.UTC),
        blueTeam = this.blue.toMatchTeamResult(),
        orangeTeam = this.orange.toMatchTeamResult(),
    )
}

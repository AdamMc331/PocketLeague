package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.datetime.Instant

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
        dateUTC = this.dateUTC?.let(Instant.Companion::parse),
        blueTeam = this.blue.toMatchTeamResult(),
        orangeTeam = this.orange.toMatchTeamResult(),
        stage = this.stage?.toEventStage() ?: EventStage(),
    )
}

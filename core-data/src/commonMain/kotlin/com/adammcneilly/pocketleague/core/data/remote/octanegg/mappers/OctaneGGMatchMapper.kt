package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGGameOverview
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.toFormat
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.toGameOverview
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Format
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult

/**
 * Converts an [OctaneGGMatch] to a [Match] entity.
 */
fun OctaneGGMatch.toMatch(): Match {
    return Match(
        id = this.id.orEmpty(),
        event = this.event?.toEvent() ?: Event(),
        dateUTC = this.dateUTC,
        blueTeam = this.blue?.toMatchTeamResult() ?: MatchTeamResult(),
        orangeTeam = this.orange?.toMatchTeamResult() ?: MatchTeamResult(),
        stage = this.stage?.toEventStage() ?: EventStage(),
        format = this.format?.toFormat() ?: Format(),
        gameOverviews = this.games?.map(OctaneGGGameOverview::toGameOverview).orEmpty()
    )
}

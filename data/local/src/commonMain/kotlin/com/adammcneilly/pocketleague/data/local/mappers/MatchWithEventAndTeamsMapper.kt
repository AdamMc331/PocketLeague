package com.adammcneilly.pocketleague.data.local.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Format
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams

fun MatchWithEventAndTeams.toMatch(): Match {
    val blueTeamGameWins = this.localMatchBlueTeamGoals.toInt()
    val orangeTeamGameWins = this.localMatchOrangeTeamGoals.toInt()

    val blueTeamWinner = blueTeamGameWins > orangeTeamGameWins
    val orangeTeamWinner = orangeTeamGameWins > blueTeamGameWins

    return Match(
        id = this.localMatchId,
        event = Event(
            id = this.localEventId,
            name = this.localEventName,
            startDateUTC = this.localEventStartDateUTC,
            endDateUTC = this.localEventEndDateUTC,
            imageURL = this.localEventImageURL,
            // ADAM NEEDS TO STORE THIS,
            stages = emptyList(),
            tier = EventTier.valueOf(this.localEventTier),
            mode = this.localEventMode,
            region = EventRegion.valueOf(this.localEventRegion),
            lan = this.localEventLan,
            // Need to store prize
            prize = null,
        ),
        dateUTC = this.localMatchDateUTC,
        blueTeam = MatchTeamResult(
            score = blueTeamGameWins,
            winner = blueTeamWinner,
            team = Team(
                id = this.blueTeamId,
                name = this.blueTeamName,
                imageUrl = this.blueTeamImageURL,
                isFavorite = this.blueTeamIsFavorite,
            ),
            // We should store these
            players = emptyList(),
            stats = null,
        ),
        orangeTeam = MatchTeamResult(
            score = orangeTeamGameWins,
            winner = orangeTeamWinner,
            team = Team(
                id = this.orangeTeamId,
                name = this.orangeTeamName,
                imageUrl = this.orangeTeamImageURL,
                isFavorite = this.orangeTeamIsFavorite,
            ),
            // We should store these
            players = emptyList(),
            stats = null,
        ),
        stage = EventStage(
            id = "TODO",
            name = "TODO",
            region = "TODO",
            startDateUTC = "TODO",
            endDateUTC = "TODO",
            liquipedia = "TODO",
            qualifier = false,
            lan = false,
        ),
        format = Format(
            type = this.localMatchFormatType,
            length = this.localMatchFormatLength.toInt(),
        ),
        // NEED TO STORE GAME INFO
        gameOverviews = emptyList(),
    )
}

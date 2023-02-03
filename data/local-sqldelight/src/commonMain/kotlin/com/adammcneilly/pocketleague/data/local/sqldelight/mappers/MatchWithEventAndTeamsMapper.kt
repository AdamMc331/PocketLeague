package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Format
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult
import com.adammcneilly.pocketleague.core.models.Stats
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams

fun MatchWithEventAndTeams.toMatch(): Match {
    val blueTeamGameWins = this.localMatchblueTeamGameWins.toInt()
    val orangeTeamGameWins = this.localMatchorangeTeamGameWins.toInt()

    val blueTeamWinner = blueTeamGameWins > orangeTeamGameWins
    val orangeTeamWinner = orangeTeamGameWins > blueTeamGameWins

    return Match(
        id = this.localMatchId,
        event = mapEvent(),
        dateUTC = this.localMatchDateUTC,
        blueTeam = mapBlueTeamResult(blueTeamGameWins, blueTeamWinner),
        orangeTeam = mapOrangeTeamResult(orangeTeamGameWins, orangeTeamWinner),
        stage = mapEventStage(),
        format = mapFormat(),
        // NEED TO STORE GAME INFO
        gameOverviews = emptyList(),
    )
}

private fun MatchWithEventAndTeams.mapEvent() = Event(
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
)

private fun MatchWithEventAndTeams.mapFormat() = Format(
    type = this.localMatchFormatType,
    length = this.localMatchFormatLength.toInt(),
)

private fun mapEventStage() = EventStage(
    id = "TODO",
    name = "TODO",
    region = "TODO",
    startDateUTC = "TODO",
    endDateUTC = "TODO",
    liquipedia = "TODO",
    qualifier = false,
    lan = false,
)

private fun MatchWithEventAndTeams.mapBlueTeamResult(
    blueTeamGameWins: Int,
    blueTeamWinner: Boolean,
) = MatchTeamResult(
    score = blueTeamGameWins,
    winner = blueTeamWinner,
    team = Team(
        id = this.blueTeamId,
        name = this.blueTeamName,
        imageUrl = this.blueTeamImageURL,
        isFavorite = this.blueTeamIsFavorite,
    ),
    stats = Stats(
        core = CoreStats(
            shots = this.localMatchBlueTeamTotalShots.toInt(),
            goals = this.localMatchBlueTeamTotalGoals.toInt(),
            saves = this.localMatchBlueTeamTotalSaves.toInt(),
            assists = this.localMatchBlueTeamTotalAssists.toInt(),
            score = this.localMatchBlueTeamTotalScore.toInt(),
            shootingPercentage = this.localMatchOrangeTeamShootingPercentage.toFloat(),
        ),
    ),
    // We should store these
    players = emptyList(),
)

private fun MatchWithEventAndTeams.mapOrangeTeamResult(
    orangeTeamGameWins: Int,
    orangeTeamWinner: Boolean,
) = MatchTeamResult(
    score = orangeTeamGameWins,
    winner = orangeTeamWinner,
    team = Team(
        id = this.orangeTeamId,
        name = this.orangeTeamName,
        imageUrl = this.orangeTeamImageURL,
        isFavorite = this.orangeTeamIsFavorite,
    ),
    stats = Stats(
        core = CoreStats(
            shots = this.localMatchOrangeTeamTotalShots.toInt(),
            goals = this.localMatchOrangeTeamTotalGoals.toInt(),
            saves = this.localMatchOrangeTeamTotalSaves.toInt(),
            assists = this.localMatchOrangeTeamTotalAssists.toInt(),
            score = this.localMatchOrangeTeamTotalScore.toInt(),
            shootingPercentage = this.localMatchOrangeTeamShootingPercentage.toFloat(),
        ),
    ),
    // We should store these
    players = emptyList(),
)

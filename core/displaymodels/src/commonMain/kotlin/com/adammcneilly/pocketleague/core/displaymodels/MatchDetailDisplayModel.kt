package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.datetime.TimeZone
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.StageRound

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy"
private const val MATCH_TIME_FORMAT = "HH:mm"

/**
 * User friendly presentation of detailed info about a match between two teams.
 */
data class MatchDetailDisplayModel(
    val matchId: Match.Id,
    val localDate: String,
    val localTime: String,
    val eventName: String,
    val stageName: String,
    val relativeDateTime: String,
    val orangeTeamResult: MatchTeamResultDisplayModel,
    val blueTeamResult: MatchTeamResultDisplayModel,
    val round: StageRound,
    val isLive: Boolean = false,
    val isPlaceholder: Boolean = false,
) {
    companion object {
        val placeholder = MatchDetailDisplayModel(
            matchId = Match.Id(""),
            localDate = "",
            localTime = "",
            eventName = "",
            stageName = "",
            relativeDateTime = "",
            isLive = false,
            orangeTeamResult = MatchTeamResultDisplayModel.placeholder,
            blueTeamResult = MatchTeamResultDisplayModel.placeholder,
            round = StageRound(0, ""),
            isPlaceholder = true,
        )
    }
}

/**
 * Converts a [Match] to a [MatchDetailDisplayModel].
 */
fun Match.toDetailDisplayModel(timeProvider: TimeProvider): MatchDetailDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return MatchDetailDisplayModel(
        matchId = this.id,
        orangeTeamResult = this.orangeTeam.toDisplayModel(),
        blueTeamResult = this.blueTeam.toDisplayModel(),
        localDate = this.dateUTC?.let { date ->
            dateTimeFormatter.formatUTCString(
                utcString = date,
                formatPattern = MATCH_DATE_FORMAT,
                timeZone = TimeZone.SYSTEM_DEFAULT,
            )
        }.orEmpty(),
        localTime = this.dateUTC?.let { date ->
            dateTimeFormatter.formatUTCString(
                utcString = date,
                formatPattern = MATCH_TIME_FORMAT,
                timeZone = TimeZone.SYSTEM_DEFAULT,
            )
        }.orEmpty(),
        eventName = this.event.name,
        stageName = this.stage.name,
        relativeDateTime = this.dateUTC?.let { date ->
            dateTimeFormatter.getRelativeTimestamp(date, timeProvider)
        }.orEmpty(),
        isLive = false,
        round = this.round,
    )
}

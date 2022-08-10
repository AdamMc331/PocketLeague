package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateUtils
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.datetime.TimeZone

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy"
private const val MATCH_TIME_FORMAT = "HH:mm"

/**
 * User friendly presentation of detailed info about a match between two teams.
 */
data class MatchDetailDisplayModel(
    val matchId: String = "",
    val orangeTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val blueTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val localDate: String = "",
    val localTime: String = "",
    val eventName: String = "",
    val stageName: String = "",
    val relativeDateTime: String = "",
    val isPlaceholder: Boolean = false,
    val isLive: Boolean = true, // This is for demo purposes, but this should be default false.
)

/**
 * Converts a [Match] to a [MatchDetailDisplayModel].
 */
fun Match.toDetailDisplayModel(): MatchDetailDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return MatchDetailDisplayModel(
        matchId = this.id,
        orangeTeamResult = this.orangeTeam.toDisplayModel(),
        blueTeamResult = this.blueTeam.toDisplayModel(),
        localDate = this.dateUTC?.let { date ->
            dateTimeFormatter.formatInstant(
                instant = date,
                formatPattern = MATCH_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        localTime = this.dateUTC?.let { date ->
            dateTimeFormatter.formatInstant(
                instant = date,
                formatPattern = MATCH_TIME_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        eventName = this.event.name,
        stageName = this.stage.name,
        relativeDateTime = this.dateUTC?.let { date ->
            DateUtils.getRelativeTimestamp(date)
        }.orEmpty(),
    )
}

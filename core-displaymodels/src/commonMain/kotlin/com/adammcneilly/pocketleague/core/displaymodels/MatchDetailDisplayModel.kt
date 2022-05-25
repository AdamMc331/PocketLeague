package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Match

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy HH:mm"

/**
 * User friendly presentation of detailed info about a match between two teams.
 */
data class MatchDetailDisplayModel(
    val orangeTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val blueTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val date: String = "",
    val eventName: String = "",
    val stageName: String = "",
)

/**
 * Converts a [Match] to a [MatchDetailDisplayModel].
 */
fun Match.toDetailDisplayModel(): MatchDetailDisplayModel {
    val dateTimeFormatter = DateTimeFormatter()

    return MatchDetailDisplayModel(
        orangeTeamResult = this.orangeTeam.toDisplayModel(),
        blueTeamResult = this.blueTeam.toDisplayModel(),
        date = this.date?.let { date ->
            dateTimeFormatter.formatLocalDateTime(date, MATCH_DATE_FORMAT)
        }.orEmpty(),
        eventName = this.event.name,
        stageName = this.stage.name,
    )
}

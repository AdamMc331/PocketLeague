package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy HH:mm"

/**
 * User friendly presentation of detailed info about a match between two teams.
 *
 * @property[matchId] A unique identifier of the match.
 * @property[blueTeamResult] UI friendly info about the blue team's performance in this match.
 * @property[orangeTeamResult] UI friendly info about the orange team's performance in this match.
 * @property[date] User friendly representation of when this match happened.
 * @property[eventName] The name of the event that this match occurred in.
 * @property[relativeDate] The relative timestamp since this match occurred, like 1h ago or 3d ago.
 */
data class MatchDetailDisplayModel(
    val matchId: String = "",
    val blueTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val orangeTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val date: String = "",
    val eventName: String = "",
    val relativeDate: String = "",
)

/**
 * Converts a [Match] to a [MatchDetailDisplayModel].
 */
fun Match.toDetailDisplayModel(): MatchDetailDisplayModel {
    val dateTimeFormatter = DateTimeFormatter()

    return MatchDetailDisplayModel(
        matchId = this.id,
        orangeTeamResult = this.orangeTeam.toDisplayModel(),
        blueTeamResult = this.blueTeam.toDisplayModel(),
        date = this.date?.let { date ->
            dateTimeFormatter.formatLocalDateTime(date, MATCH_DATE_FORMAT)
        }.orEmpty(),
        eventName = this.event.name,
        relativeDate = this.date?.getRelativeTimestamp().orEmpty(),
    )
}

private const val HOURS_IN_DAY = 24

private fun LocalDateTime.getRelativeTimestamp(): String {
    val now = Clock.System.now()
    val matchInstant = this.toInstant(TimeZone.currentSystemDefault())

    val duration = now.minus(matchInstant)

    return when {
        duration.inWholeHours < HOURS_IN_DAY -> {
            "${duration.inWholeHours}h ago"
        }
        else -> {
            "${duration.inWholeDays}d ago"
        }
    }
}

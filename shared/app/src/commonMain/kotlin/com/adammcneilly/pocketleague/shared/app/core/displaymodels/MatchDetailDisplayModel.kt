package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeZone
import com.adammcneilly.pocketleague.shared.app.core.models.Match
import com.adammcneilly.pocketleague.shared.app.core.models.StageRound

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy"
private const val MATCH_TIME_FORMAT = "HH:mm"

/**
 * User friendly presentation of detailed info about a match between two teams.
 */
data class MatchDetailDisplayModel(
    val matchId: String,
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
    constructor(
        match: Match,
        dateTimeFormatter: DateTimeFormatter,
        timeProvider: TimeProvider,
    ) : this(
        matchId = match.id,
        orangeTeamResult = MatchTeamResultDisplayModel(match.orangeTeam),
        blueTeamResult = MatchTeamResultDisplayModel(match.blueTeam),
        localDate = match.dateUTC?.toMatchDateString(dateTimeFormatter).orEmpty(),
        localTime = match.dateUTC?.toMatchTimeString(dateTimeFormatter).orEmpty(),
        eventName = match.event.name,
        stageName = match.stage.name,
        relativeDateTime = match.dateUTC?.toRelativeTimestamp(timeProvider).orEmpty(),
        isLive = false,
        round = match.round,
    )

    companion object {
        val placeholder = MatchDetailDisplayModel(
            matchId = "",
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

private fun String.toMatchDateString(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    return dateTimeFormatter.formatUTCString(
        utcString = this,
        formatPattern = MATCH_DATE_FORMAT,
        timeZone = TimeZone.SYSTEM_DEFAULT,
    )
}

private fun String.toMatchTimeString(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    return dateTimeFormatter.formatUTCString(
        utcString = this,
        formatPattern = MATCH_TIME_FORMAT,
        timeZone = TimeZone.SYSTEM_DEFAULT,
    )
}

private fun String.toRelativeTimestamp(
    timeProvider: TimeProvider,
): String? {
    return DateTimeFormatter.getRelativeTimestamp(
        utcString = this,
        timeProvider = timeProvider,
    )
}

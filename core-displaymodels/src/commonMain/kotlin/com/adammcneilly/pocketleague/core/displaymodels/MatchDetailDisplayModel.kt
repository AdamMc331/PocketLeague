package com.adammcneilly.pocketleague.core.displaymodels

/**
 * User friendly presentation of detailed info about a match between two teams.
 */
data class MatchDetailDisplayModel(
    val orangeTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
    val blueTeamResult: MatchTeamResultDisplayModel = MatchTeamResultDisplayModel(),
)

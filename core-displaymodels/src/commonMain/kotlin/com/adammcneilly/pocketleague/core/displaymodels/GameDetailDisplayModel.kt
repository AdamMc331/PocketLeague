package com.adammcneilly.pocketleague.core.displaymodels

/**
 * User friendly presentation for detailed information about a game.
 */
data class GameDetailDisplayModel(
    val orangeTeamResult: GameTeamResultDisplayModel = GameTeamResultDisplayModel(),
    val blueTeamResult: GameTeamResultDisplayModel = GameTeamResultDisplayModel(),
    val map: String = "",
)

package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Game

/**
 * User friendly presentation for detailed information about a game.
 */
data class GameDetailDisplayModel(
    val orangeTeamResult: GameTeamResultDisplayModel = GameTeamResultDisplayModel(),
    val blueTeamResult: GameTeamResultDisplayModel = GameTeamResultDisplayModel(),
    val map: String = "",
    val gameNumber: String = "",
)

/**
 * Converts a [Game] to a [GameDetailDisplayModel].
 */
fun Game.toDetailDisplayModel(): GameDetailDisplayModel {
    return GameDetailDisplayModel(
        orangeTeamResult = this.orange.toDisplayModel(),
        blueTeamResult = this.blue.toDisplayModel(),
        map = this.map,
        gameNumber = this.number.toString(),
    )
}

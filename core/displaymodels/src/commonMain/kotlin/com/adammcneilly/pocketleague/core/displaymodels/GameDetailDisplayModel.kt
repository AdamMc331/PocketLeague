package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Game

/**
 * User friendly presentation for detailed information about a game.
 */
data class GameDetailDisplayModel(
    val orangeTeamResult: GameTeamResultDisplayModel,
    val blueTeamResult: GameTeamResultDisplayModel,
    val map: String,
    val gameNumber: String,
    val isPlaceholder: Boolean = false,
) {

    companion object {
        val placeholder = GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel.placeholder,
            blueTeamResult = GameTeamResultDisplayModel.placeholder,
            map = "",
            gameNumber = "",
            isPlaceholder = true,
        )
    }
}

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

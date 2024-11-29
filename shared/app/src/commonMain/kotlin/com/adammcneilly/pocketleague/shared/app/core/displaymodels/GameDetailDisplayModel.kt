package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.models.Game

/**
 * User friendly presentation for detailed information about a game.
 */
data class GameDetailDisplayModel(
    val orangeTeamResult: GameTeamResultDisplayModel,
    val blueTeamResult: GameTeamResultDisplayModel,
    val map: String,
    val gameNumber: String,
    val otLabel: String?,
    val isPlaceholder: Boolean = false,
) {
    constructor(
        game: Game,
        dateTimeFormatter: DateTimeFormatter,
    ) : this(
        orangeTeamResult = GameTeamResultDisplayModel(game.orange),
        blueTeamResult = GameTeamResultDisplayModel(game.blue),
        map = game.map,
        gameNumber = game.number.toString(),
        otLabel = game.getOtLabel(dateTimeFormatter),
    )

    companion object {
        val placeholder = GameDetailDisplayModel(
            orangeTeamResult = GameTeamResultDisplayModel.placeholder,
            blueTeamResult = GameTeamResultDisplayModel.placeholder,
            map = "",
            gameNumber = "",
            otLabel = null,
            isPlaceholder = true,
        )
    }
}

private fun Game.getOtLabel(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    val extraTime = this.duration - Game.GAME_DEFAULT_DURATION_SECONDS
    val otLabel = "OT +${dateTimeFormatter.formatExtraTime(extraTime)}"

    return otLabel.takeIf { extraTime != 0 }
}

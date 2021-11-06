package com.adammcneilly.pocketleague.player.ui

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.ui.FlagResProvider

data class PlayerDisplayModel(
    val flagResId: Int,
    val gamerTag: String,
    val realName: String,
    val notes: String?,
)

fun Player.toDisplayModel(
    flagResProvider: FlagResProvider,
): PlayerDisplayModel {
    return PlayerDisplayModel(
        flagResId = flagResProvider.getFlagRes(this.countryCode),
        gamerTag = this.gamerTag,
        realName = "(${this.realName})",
        notes = this.notes?.let { notes ->
            "($notes)"
        },
    )
}

package com.adammcneilly.pocketleague.player.ui

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage

data class PlayerDisplayModel(
    val flagImage: UIImage,
    val gamerTag: String,
    val realName: String,
    val notes: String?,
)

fun Player.toDisplayModel(
    flagResProvider: FlagResProvider,
): PlayerDisplayModel {
    val flagResId = flagResProvider.getFlagRes(this.countryCode)
    return PlayerDisplayModel(
        flagImage = UIImage.Resource(flagResId),
        gamerTag = this.gamerTag,
        realName = "(${this.realName})",
        notes = this.notes?.let { notes ->
            "($notes)"
        },
    )
}

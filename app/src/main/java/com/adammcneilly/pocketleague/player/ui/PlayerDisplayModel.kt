package com.adammcneilly.pocketleague.player.ui

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage

/**
 * A display model to present a player in a user friendly manner on the UI.
 */
data class PlayerDisplayModel(
    val flagImage: UIImage,
    val gamerTag: String,
    val realName: String,
    val notes: String?,
)

/**
 * Converts a [Player] to a [PlayerDisplayModel] so that we can present the player in a user friendly
 * manner.
 *
 * @param[flagResProvider] A utility for mapping the player's country code to the relevant flag
 * resource.
 */
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

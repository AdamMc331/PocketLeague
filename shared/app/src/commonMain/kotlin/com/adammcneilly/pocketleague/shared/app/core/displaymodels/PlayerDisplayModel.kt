package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.shared.app.core.models.Player

/**
 * User friendly representation of a player.
 */
data class PlayerDisplayModel(
    val id: String,
    val name: String,
    val tag: String,
    val role: String?,
    val countryFlagEmojiUnicode: String,
    val isPlaceholder: Boolean = false,
) {
    constructor(player: Player) : this(
        id = player.id,
        name = player.name,
        tag = player.tag,
        role = player.roleString(),
        countryFlagEmojiUnicode = LocaleHelper.getFlagEmoji(player.countryCode),
    )

    companion object {
        val placeholder = PlayerDisplayModel(
            id = "",
            name = "",
            tag = "",
            role = null,
            countryFlagEmojiUnicode = "",
            isPlaceholder = true,
        )
    }
}

private fun Player.roleString(): String? {
    return when {
        this.isCoach -> "(C)"
        this.isSubstitute -> "(S)"
        else -> null
    }
}

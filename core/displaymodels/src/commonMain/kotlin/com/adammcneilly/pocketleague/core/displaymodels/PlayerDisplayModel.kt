package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.core.locale.provideLocaleHelper
import com.adammcneilly.pocketleague.core.models.Player

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

/**
 * Converts a [Player] to its corresponding [PlayerDisplayModel]
 */
fun Player.toDisplayModel(
    localeHelper: LocaleHelper = provideLocaleHelper(),
): PlayerDisplayModel {
    // Maybe roles can be an enum instead of strings?
    val role = when {
        this.isCoach -> "(C)"
        this.isSubstitute -> "(S)"
        else -> null
    }

    return PlayerDisplayModel(
        id = this.id,
        name = this.name,
        tag = this.tag,
        role = role,
        countryFlagEmojiUnicode = localeHelper.getFlagEmoji(countryCode),
    )
}

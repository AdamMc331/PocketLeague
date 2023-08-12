package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.core.locale.provideLocaleHelper
import com.adammcneilly.pocketleague.core.models.Player

/**
 * User friendly representation of a player.
 */
data class PlayerDisplayModel(
    val id: String,
    val tag: String,
    val role: String?,
    val countryFlagEmojiUnicode: String,
)

/**
 * Converts a [Player] to its corresponding [PlayerDisplayModel]
 */
fun Player.toDisplayModel(
    localeHelper: LocaleHelper = provideLocaleHelper(),
): PlayerDisplayModel {
    // Maybe roles can be an enum instead of strings?
    val role = when {
        this.isCoach -> "Coach"
        this.isSubstitute -> "Substitute"
        else -> null
    }

    return PlayerDisplayModel(
        id = this.id,
        tag = this.tag,
        role = role,
        countryFlagEmojiUnicode = localeHelper.getFlagEmoji(countryCode),
    )
}

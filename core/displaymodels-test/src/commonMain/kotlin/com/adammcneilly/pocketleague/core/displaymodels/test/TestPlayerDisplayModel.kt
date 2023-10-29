@file:Suppress("MaxLineLength")

package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel

private val defaultPlayer =
    PlayerDisplayModel(
        id = "",
        name = "",
        tag = "",
        role = null,
        countryFlagEmojiUnicode = "",
    )

private fun PlayerDisplayModel.Companion.joyo(): PlayerDisplayModel {
    return defaultPlayer.copy(
        tag = "Joyo",
        name = "Joe Young",
        countryFlagEmojiUnicode = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F",
    )
}

private fun PlayerDisplayModel.Companion.juicy(): PlayerDisplayModel {
    return defaultPlayer.copy(
        tag = "juicy",
        name = "Charles Sabiani",
        countryFlagEmojiUnicode = "\uD83C\uDDEB\uD83C\uDDF7",
    )
}

private fun PlayerDisplayModel.Companion.kash(): PlayerDisplayModel {
    return defaultPlayer.copy(
        tag = "Kash",
        name = "Kurtis Cannon",
        countryFlagEmojiUnicode = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F",
    )
}

private fun PlayerDisplayModel.Companion.aztral(): PlayerDisplayModel {
    return defaultPlayer.copy(
        tag = "AztraL",
        name = "Maëllo Ernst",
        role = "(S)",
        countryFlagEmojiUnicode = "\uD83C\uDDE7\uD83C\uDDEA",
    )
}

private fun PlayerDisplayModel.Companion.noah(): PlayerDisplayModel {
    return defaultPlayer.copy(
        tag = "noah",
        name = "Noah Hinder",
        role = "(C)",
        countryFlagEmojiUnicode = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F",
    )
}

fun PlayerDisplayModel.Companion.moistRoster(): List<PlayerDisplayModel> {
    return listOf(
        joyo(),
        juicy(),
        kash(),
        aztral(),
        noah(),
    )
}

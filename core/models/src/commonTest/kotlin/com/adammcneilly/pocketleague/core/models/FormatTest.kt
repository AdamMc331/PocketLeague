package com.adammcneilly.pocketleague.core.models

import kotlin.test.Test
import kotlin.test.assertEquals

class FormatTest {

    @Test
    fun getNumToWinBestFormat() {
        val format = Format(
            type = "best",
            length = 7,
        )

        assertEquals(4, format.numGamesRequiredToWin())
    }

    @Test
    fun getNumToWinUnknownFormat() {
        val format = Format(
            type = "???",
            length = 7,
        )

        assertEquals(7, format.numGamesRequiredToWin())
    }
}

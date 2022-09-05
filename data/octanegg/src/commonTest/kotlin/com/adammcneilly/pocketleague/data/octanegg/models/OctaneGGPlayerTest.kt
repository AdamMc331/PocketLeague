package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Player
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGPlayerTest {

    @Test
    fun convertValidPlayer() {
        val octanePlayer = OctaneGGPlayer(
            id = "playerId",
            slug = "slug",
            tag = "tag",
            country = "country",
        )

        val domainPlayer = octanePlayer.toPlayer()

        val expectedPlayer = Player(
            id = "playerId",
            slug = "slug",
            tag = "tag",
            country = "country",
        )

        assertEquals(expectedPlayer, domainPlayer)
    }

    @Test
    fun convertDefaultPlayer() {
        val octanePlayer = OctaneGGPlayer()

        val domainPlayer = octanePlayer.toPlayer()

        val expectedPlayer = Player(
            id = "",
            slug = "",
            tag = "",
            country = "",
        )

        assertEquals(expectedPlayer, domainPlayer)
    }
}

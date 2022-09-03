package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OctaneGGPlayerStatsTest {

    @Test
    fun convertValidPlayerStats() {
        val octanePlayerStats = OctaneGGPlayerStats(
            player = OctaneGGPlayer(
                name = "Player Name",
            ),
            stats = OctaneGGStats(
                core = OctaneGGCoreStats(
                    score = 5,
                ),
            ),
        )

        val gamePlayerResult = octanePlayerStats.toGamePlayerResult()

        val expectedPlayerResult = GamePlayerResult(
            player = octanePlayerStats.player?.toPlayer()!!,
            stats = octanePlayerStats.stats?.toStats()!!,
        )

        assertEquals(expectedPlayerResult, gamePlayerResult)
    }

    @Test
    fun convertWithoutPlayer() {
        val octanePlayerResult = OctaneGGPlayerStats(
            player = null,
            stats = OctaneGGStats(
                core = OctaneGGCoreStats(
                    score = 1,
                ),
            ),
        )

        assertFailsWith(IllegalArgumentException::class) {
            octanePlayerResult.toGamePlayerResult()
        }
    }

    @Test
    fun convertWithoutStats() {
        val octanePlayerResult = OctaneGGPlayerStats(
            player = OctaneGGPlayer(
                name = "Player Name",
            ),
            stats = null,
        )

        assertFailsWith(IllegalArgumentException::class) {
            octanePlayerResult.toGamePlayerResult()
        }
    }
}

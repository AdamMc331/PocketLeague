package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Stats
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OctaneGGStatsTest {

    @Test
    fun convertValidStats() {
        val octaneStats = OctaneGGStats(
            core = OctaneGGCoreStats(
                shots = 1,
                goals = 2,
                saves = 3,
                assists = 4,
                score = 5,
                shootingPercentage = 6.0F,
            ),
        )

        val domainStats = octaneStats.toStats()

        val expectedStats = Stats(
            core = octaneStats.core?.toCoreStats()!!,
        )

        assertEquals(expectedStats, domainStats)
    }

    @Test
    fun convertWithoutCoreStats() {
        val octaneStats = OctaneGGStats(
            core = null,
        )

        assertFailsWith(IllegalArgumentException::class) {
            octaneStats.toStats()
        }
    }
}

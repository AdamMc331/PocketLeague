package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.CoreStats
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGCoreStatsTest {

    @Test
    fun mapValidCoreStats() {
        val octaneStats = OctaneGGCoreStats(
            shots = 1,
            goals = 2,
            saves = 3,
            assists = 4,
            score = 5,
            shootingPercentage = 6.0F,
        )

        val domainStats = octaneStats.toCoreStats()

        val expectedStats = CoreStats(
            shots = 1,
            goals = 2,
            saves = 3,
            assists = 4,
            score = 5,
            shootingPercentage = 6.0F,
        )

        assertEquals(expectedStats, domainStats)
    }

    @Test
    fun mapDefaultCoreStats() {
        val octaneStats = OctaneGGCoreStats()

        val domainStats = octaneStats.toCoreStats()

        val expectedStats = CoreStats(
            shots = 0,
            goals = 0,
            saves = 0,
            assists = 0,
            score = 0,
            shootingPercentage = 0.0F,
        )

        assertEquals(expectedStats, domainStats)
    }
}

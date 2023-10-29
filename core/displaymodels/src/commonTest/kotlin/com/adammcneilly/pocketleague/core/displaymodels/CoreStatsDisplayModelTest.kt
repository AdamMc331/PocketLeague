package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.CoreStats
import kotlin.test.Test
import kotlin.test.assertEquals

class CoreStatsDisplayModelTest {
    @Test
    fun mapFromCoreStats() {
        val coreStats = CoreStats(
            score = 0,
            goals = 1,
            assists = 2,
            saves = 3,
            shots = 4,
        )

        val displayModel = coreStats.toDisplayModel()

        with(displayModel) {
            assertEquals(coreStats.score, score)
            assertEquals(coreStats.goals, goals)
            assertEquals(coreStats.assists, assists)
            assertEquals(coreStats.saves, saves)
            assertEquals(coreStats.shots, shots)
        }
    }
}

package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import com.adammcneilly.pocketleague.core.models.GameTeamResult
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Stats
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameTeamResultDisplayModelTest {
    @Test
    fun mapWithSortedPlayers() {
        val players =
            listOf(
                GamePlayerResult(
                    player =
                        Player(
                            tag = "AdamMc331",
                            countryCode = "US",
                        ),
                    stats =
                        Stats(
                            core =
                                CoreStats(
                                    score = 123,
                                ),
                        ),
                ),
                GamePlayerResult(
                    player =
                        Player(
                            tag = "PleasantlyPlump",
                            countryCode = "CA",
                        ),
                    stats =
                        Stats(
                            core =
                                CoreStats(
                                    score = 1234,
                                ),
                        ),
                ),
                GamePlayerResult(
                    player =
                        Player(
                            tag = "mTeo",
                            countryCode = "US",
                        ),
                    stats =
                        Stats(
                            core =
                                CoreStats(
                                    score = 500,
                                ),
                        ),
                ),
            )

        val gameTeamResult =
            GameTeamResult(
                players = players,
                goals = 4,
                winner = true,
            )

        val mappedModel = gameTeamResult.toDisplayModel()

        assertEquals("PleasantlyPlump", mappedModel.players[0].player.tag)
        assertEquals("mTeo", mappedModel.players[1].player.tag)
        assertEquals("AdamMc331", mappedModel.players[2].player.tag)
        assertEquals(4, mappedModel.goals)
        assertTrue(mappedModel.winner)
    }
}

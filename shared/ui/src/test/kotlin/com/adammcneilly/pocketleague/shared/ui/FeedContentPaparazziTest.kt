package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForAllRegions
import com.adammcneilly.pocketleague.core.displaymodels.test.springMajor
import com.adammcneilly.pocketleague.core.displaymodels.test.worldChampionship
import org.junit.Rule
import kotlin.test.Test

class FeedContentPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderFeedContent() {
        paparazzi.snapshotScreen(
            useDarkTheme = false,
            screenPaddingDp = 0,
        ) {
            val events = EventSummaryDisplayModel.springInvitationalForAllRegions().take(3)
            val springMajor = EventSummaryDisplayModel.springMajor()
            val worlds = EventSummaryDisplayModel.worldChampionship()

            val groups = listOf(
                events,
                listOf(springMajor),
                listOf(worlds),
            )

            FeedContent(
                eventGroups = groups,
            )
        }
    }
}

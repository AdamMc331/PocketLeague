package com.adammcneilly.pocketleague.shared.ui.feed

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForAllRegions
import com.adammcneilly.pocketleague.core.displaymodels.test.springMajor
import com.adammcneilly.pocketleague.core.displaymodels.test.worldChampionship
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class FeedContentPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderFeedContent() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            val regionals = EventSummaryDisplayModel.springInvitationalForAllRegions().take(3)
            val springMajor = EventSummaryDisplayModel.springMajor()
            val worlds = EventSummaryDisplayModel.worldChampionship()

            val ongoingEvents = EventGroupDisplayModel.mapFromEventList(
                events = regionals + springMajor,
            )

            val upcomingEvents = EventGroupDisplayModel.mapFromEventList(
                events = listOf(worlds),
            )

            val matchList = listOf(
                TestDisplayModel.matchDetailBlueWinner,
                TestDisplayModel.matchDetailBlueWinner,
                TestDisplayModel.matchDetailBlueWinner,
            )

            FeedContent(
                recentMatches = matchList,
                ongoingEvents = ongoingEvents,
                upcomingEvents = upcomingEvents,
                onMatchClicked = {},
                onEventClicked = {},
            )
        }
    }
}

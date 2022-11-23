package com.adammcneilly.pocketleague.android.designsystem.eventstages

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.eventStageSummary
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class EventStageListCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderCardWithRealData() {
        val displayModels = List(3) {
            TestDisplayModel.eventStageSummary
        }

        paparazzi.snapshotScreen(useDarkTheme) {
            EventStageListCard(displayModels = displayModels, onStageClicked = {})
        }
    }

    @Test
    fun renderWithPlaceholders() {
        val displayModels = List(3) {
            EventStageSummaryDisplayModel.placeholder
        }

        paparazzi.snapshotScreen(useDarkTheme) {
            EventStageListCard(displayModels = displayModels, onStageClicked = {})
        }
    }
}

package com.adammcneilly.pocketleague.android.designsystem.eventstages

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
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

    private val displayModel = EventStageSummaryDisplayModel(
        stageId = "",
        name = "Stage Name",
        startDate = "Jan 01, 2000",
        endDate = "Jan 02, 2000",
        lan = false,
        liquipedia = "",
    )

    @Test
    fun renderCardWithRealData() {
        val displayModels = List(3) {
            displayModel
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

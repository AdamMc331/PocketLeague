package com.adammcneilly.pocketleague.feature.eventdetail

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.EventStage
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class EventStageCardPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderPlaceholder() {
        val displayModel = EventStageSummaryDisplayModel.placeholder

        paparazzi.snapshotScreen(useDarkTheme) {
            EventStageCard(displayModel)
        }
    }

    @Test
    fun renderDefault() {
        val displayModel =
            EventStageSummaryDisplayModel(
                stageId = EventStage.Id(""),
                name = "Playoffs",
                startDate = "Apr 8",
                endDate = "Apr 9, 2023",
                lan = true,
                liquipedia = "",
            )

        paparazzi.snapshotScreen(useDarkTheme) {
            EventStageCard(displayModel)
        }
    }
}

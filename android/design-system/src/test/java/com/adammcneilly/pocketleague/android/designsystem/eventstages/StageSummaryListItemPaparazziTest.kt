package com.adammcneilly.pocketleague.android.designsystem.eventstages

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.testEventStageSummaryDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class StageSummaryListItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderItem() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StageSummaryListItem(displayModel = testEventStageSummaryDisplayModel)
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StageSummaryListItem(displayModel = EventStageSummaryDisplayModel.placeholder)
        }
    }
}

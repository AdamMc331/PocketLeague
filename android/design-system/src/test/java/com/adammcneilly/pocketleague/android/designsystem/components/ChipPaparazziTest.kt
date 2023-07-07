package com.adammcneilly.pocketleague.android.designsystem.components

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.ui.composables.components.TooltipChip
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class ChipPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderChip() {
        paparazzi.snapshotScreen(useDarkTheme) {
            TooltipChip(text = "Chip", tooltipText = "Tooltip")
        }
    }
}

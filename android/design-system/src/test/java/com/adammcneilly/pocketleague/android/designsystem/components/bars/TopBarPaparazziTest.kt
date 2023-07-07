package com.adammcneilly.pocketleague.android.designsystem.components.bars

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class TopBarPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderTopBar() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            TopBar(
                title = "Top App Bar",
            )
        }
    }
}

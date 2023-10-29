package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class InlineIconTextPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderWithoutIcon() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                showIcon = false,
            )
        }
    }

    @Test
    fun renderLeadingIcon() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                leadingIcon = true,
            )
        }
    }

    @Test
    fun renderTrailingIcon() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                leadingIcon = false,
            )
        }
    }
}

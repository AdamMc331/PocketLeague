package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@Ignore
class InlineIconTextPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderWithoutIcon() {
        paparazzi.snapshotScreen {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                showIcon = false,
            )
        }
    }

    @Test
    fun renderLeadingIcon() {
        paparazzi.snapshotScreen {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                leadingIcon = true,
            )
        }
    }

    @Test
    fun renderTrailingIcon() {
        paparazzi.snapshotScreen {
            InlineIconText(
                text = "Inline Icon Text",
                icon = Icons.Default.EmojiEvents,
                leadingIcon = false,
            )
        }
    }
}

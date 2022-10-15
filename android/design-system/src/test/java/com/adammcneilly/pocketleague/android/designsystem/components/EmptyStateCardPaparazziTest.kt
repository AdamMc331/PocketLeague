package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import org.junit.Rule
import org.junit.Test

class EmptyStateCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderCard() {
        paparazzi.snapshotScreen {
            EmptyStateCard(
                text = "Demo Empty State Card",
                textModifier = Modifier.padding(32.dp),
            )
        }
    }
}

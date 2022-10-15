package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.BasePaparazziTest
import org.junit.Test

class EmptyStateCardPaparazziTest : BasePaparazziTest() {

    @Test
    fun renderCard() {
        snapshotScreen {
            EmptyStateCard(
                text = "Demo Empty State Card",
                textModifier = Modifier.padding(32.dp),
            )
        }
    }
}

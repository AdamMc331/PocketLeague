package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class EmptyStateCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderCard() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            EmptyStateCard(
                text = "Demo Empty State Card",
                textModifier = Modifier.padding(32.dp),
            )
        }
    }
}

package com.adammcneilly.pocketleague.android.designsystem.team

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class CircleTeamLogoPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val userDarkTheme: Boolean = false

    @Test
    fun renderTeamLetterFallback() {
        val displayModel = TestDisplayModel.g2.copy(
            imageUrl = ThemedImageURL(),
        )

        paparazzi.snapshotScreen(userDarkTheme) {
            CircleTeamLogo(
                displayModel = displayModel,
                modifier = Modifier
                    .size(96.dp),
            )
        }
    }
}

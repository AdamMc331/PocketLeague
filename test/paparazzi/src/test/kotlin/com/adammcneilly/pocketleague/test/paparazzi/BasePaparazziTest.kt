package com.adammcneilly.pocketleague.test.paparazzi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.ui.theme.RLCS
import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * This base class allows us to write Paparazzi tests that validate composable content in both light and dark theme
 * using a parameterized test. Just extend this base class and call [snapshot] with your composable content.
 */
@RunWith(TestParameterInjector::class)
abstract class BasePaparazziTest {
    @get:Rule
    @Suppress("ktlint:standard:backing-property-naming", "VariableNaming")
    val _paparazzi = Paparazzi()

    @TestParameter
    val testInput: TestInput = TestInput.LIGHT_PHONE

    /**
     * Validates the supplied [content] in both light and dark theme.
     */
    fun snapshot(
        screenPaddingDp: Int = 16,
        content: @Composable () -> Unit,
    ) {
        _paparazzi.unsafeUpdateConfig(testInput.deviceConfig)

        _paparazzi.snapshot {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                RLCS {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(screenPaddingDp.dp),
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }

    enum class TestInput(
        val deviceConfig: DeviceConfig,
    ) {
        LIGHT_PHONE(
            deviceConfig = DeviceConfig.NEXUS_5.copy(
                nightMode = NightMode.NOTNIGHT,
            ),
        ),
        DARK_PHONE(
            deviceConfig = DeviceConfig.NEXUS_5.copy(
                nightMode = NightMode.NIGHT,
            ),
        ),
        LANDSCAPE_PHONE(
            deviceConfig = DeviceConfig.NEXUS_5.copy(
                orientation = ScreenOrientation.LANDSCAPE,
            ),
        ),
        TABLET(
            deviceConfig = DeviceConfig.PIXEL_C.copy(
                orientation = ScreenOrientation.LANDSCAPE,
            ),
        ),
    }
}

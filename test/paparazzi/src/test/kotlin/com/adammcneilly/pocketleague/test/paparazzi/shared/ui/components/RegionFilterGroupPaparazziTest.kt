package com.adammcneilly.pocketleague.test.paparazzi.shared.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.models.Region
import com.adammcneilly.pocketleague.shared.ui.components.RegionFilterGroup
import com.adammcneilly.pocketleague.shared.ui.theme.RLCS
import com.adammcneilly.pocketleague.test.paparazzi.BasePaparazziTest
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class RegionFilterGroupPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderAllSelected() {
        paparazzi.snapshot {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                RLCS {
                    RegionFilterGroup(
                        selectedRegions = Region.entries,
                    )
                }
            }
        }
    }

    @Test
    fun renderOneSelected() {
        paparazzi.snapshot {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                RLCS {
                    RegionFilterGroup(
                        selectedRegions = listOf(
                            Region.NA,
                        ),
                    )
                }
            }
        }
    }

    @Test
    fun renderTwoSelected() {
        paparazzi.snapshot {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                RLCS {
                    RegionFilterGroup(
                        selectedRegions = listOf(
                            Region.NA,
                            Region.EU,
                        ),
                    )
                }
            }
        }
    }
}

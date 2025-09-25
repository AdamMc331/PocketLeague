package com.adammcneilly.pocketleague.test.paparazzi.shared.ui.components

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.models.Region
import com.adammcneilly.pocketleague.shared.ui.components.RegionFilterGroup
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.test.paparazzi.BasePaparazziTest
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

class RegionFilterGroupPaparazziTest : BasePaparazziTest() {
    @Test
    fun renderAllSelected() {
        snapshot {
            RegionFilterGroup(
                selectedRegions = Region.entries,
            )
        }
    }

    @Test
    fun renderOneSelected() {
        snapshot {
            RegionFilterGroup(
                selectedRegions = listOf(
                    Region.NA,
                ),
            )
        }
    }

    @Test
    fun renderTwoSelected() {
        snapshot {
            RegionFilterGroup(
                selectedRegions = listOf(
                    Region.NA,
                    Region.EU,
                ),
            )
        }
    }
}

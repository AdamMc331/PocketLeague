package com.adammcneilly.pocketleague.shared.ui.swiss

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class SwissTeamResultListCardPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun render() {
        val displayModels = testDisplayModels()

        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
            ) {
                item {
                    SwissTeamResultListCard(displayModels)
                }
            }
        }
    }
}

private fun testDisplayModels() =
    listOf(
        makeResult(
            teamName = "G2 Esports",
            overline = "Qualified",
            subtitle = "3-0 | 12-3 | +9",
        ),
        makeResult(
            teamName = "Team Falcons",
            overline = "Qualified",
            subtitle = "3-0 | 12-5 | +7",
        ),
        makeResult(
            teamName = "KRÜ Esports",
            overline = "Qualified",
            subtitle = "3-1 | 14-8 | +6",
        ),
        makeResult(
            teamName = "Spacestation Gaming",
            overline = "Qualified",
            subtitle = "3-0 | 15-9 | +6",
        ),
        makeResult(
            teamName = "Complexity Gaming",
            overline = "Qualified",
            subtitle = "3-0 | 12-7 | +5",
        ),
        makeResult(
            teamName = "Moist Esports",
            overline = "Qualified",
            subtitle = "3-2 | 16-10 | +6",
        ),
        makeResult(
            teamName = "Twisted Minds",
            overline = "Qualified",
            subtitle = "3-2 | 15-11 | +4",
        ),
        makeResult(
            teamName = "Team Secret",
            overline = "Qualified",
            subtitle = "3-2 | 14-12 | +2",
        ),
        makeResult(
            teamName = "PWR",
            overline = "Eliminated",
            subtitle = "2-3 | 13-13 | 0",
        ),
        makeResult(
            teamName = "G1",
            overline = "Eliminated",
            subtitle = "2-3 | 13-13 | 0",
        ),
        makeResult(
            teamName = "Elevate",
            overline = "Eliminated",
            subtitle = "2-3 | 10-16 | -6",
        ),
        makeResult(
            teamName = "Pioneers",
            overline = "Eliminated",
            subtitle = "1-3 | 8-12 | -4",
        ),
        makeResult(
            teamName = "Oxygen Esports",
            overline = "Eliminated",
            subtitle = "1-3 | 10-14 | -4",
        ),
        makeResult(
            teamName = "Limitless",
            overline = "Eliminated",
            subtitle = "1-3 | 4-12 | -8",
        ),
        makeResult(
            teamName = "Valiant",
            overline = "Eliminated",
            subtitle = "0-3 | 1-12 | -11",
        ),
        makeResult(
            teamName = "Gaimin Gladiators",
            overline = "Eliminated",
            subtitle = "0-3 | 0-12 | -12",
        ),
    )

private fun makeResult(
    teamName: String,
    overline: String,
    subtitle: String,
): SwissTeamResultDisplayModel {
    val team = TestDisplayModel.g2.copy(
        name = teamName,
    )

    return SwissTeamResultDisplayModel(
        team = team,
        overline = overline,
        subtitle = subtitle,
    )
}
